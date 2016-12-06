package br.ufpe.cin.transformationserver.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Reader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;

import org.apache.commons.codec.binary.Base64;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.epsilon.egl.EglFileGeneratingTemplateFactory;
import org.eclipse.epsilon.egl.EglTemplateFactoryModuleAdapter;
import org.eclipse.epsilon.emc.emf.InMemoryEmfModel;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.record.City;
import com.maxmind.geoip2.record.Country;
import com.maxmind.geoip2.record.Location;
import com.maxmind.geoip2.record.Postal;
import com.maxmind.geoip2.record.Subdivision;

import br.ufpe.cin.transformationserver.domain.Transformation;
import br.ufpe.cin.transformationserver.domain.repository.TransformationRepository;
import br.ufpe.cin.transformationserver.util.ResourceLoader;
import uk.ac.kent.cs.kmf.util.ILog;
import uk.ac.kent.cs.kmf.util.OutputStreamLog;
import de.ikv.emf.qvt.EMFQvtProcessorImpl;
import de.ikv.medini.qvt.QVTProcessorConsts;

@Service
public class TransformationServiceImpl implements TransformationService{

	@Autowired
	private TransformationRepository transformationRepository;
	private ResourceLoader resourceLoader = new ResourceLoader();
	 
	private ILog logger;
	private EMFQvtProcessorImpl processorImpl;
	protected ResourceSet resourceSet;

	private String sourceModelURI;
	private String sourceMetamodelURI;
	private String targetModelURI;
	private String targetMetamodelURI;
	private String qvtRule;
	private String qvtRuleTransformation;
	private String qvtRuleDirection;
	private String baseDirectory; 

	private Resource targetResource;
	private EPackageImpl targetPackage;

	private String eglRule;

	public String getTransformationCodeEER2Relational(Transformation t){
		String out = "";
		
		setBaseDirectory("c:\\transformation\\eer2relational\\"+t.getSourceName()+"\\");
		
		new File(getBaseDirectory()).mkdir();
		
		setSourceModelURI(getBaseDirectory()+t.getSourceName()+".eer");
		setTargetModelURI(getBaseDirectory()+t.getSourceName()+".relational");
		
		setSourceMetamodelURI(resourceLoader.getPath("transformation/eer2relational/eermm.ecore"));
		setTargetMetamodelURI(resourceLoader.getPath("transformation/eer2relational/relational.ecore"));
		setQvtRule(resourceLoader.getPath("transformation/eer2relational/eerToRelational.qvt"));
		setQvtRuleTransformation("eerTorelational");
		setQvtRuleDirection("rdbm");
		setEglRule(resourceLoader.getPath("transformation/eer2relational/eer2SQL.egl"));

		
		byte[] data = Base64.decodeBase64(t.getSourceData().getBytes());
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(new File(getSourceModelURI()));
			try {
				fos.write(data);
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

		// M2M
		initMediniQVT(System.out);
		launch();

		// M2T
		// Construct the EGL module
		EglTemplateFactoryModuleAdapter module =  
				new EglTemplateFactoryModuleAdapter(new EglFileGeneratingTemplateFactory());

		// Wrap it as an InMemoryEmfModel
		InMemoryEmfModel m = new InMemoryEmfModel("M", getTargetResource(), getTargetPackage());

		try {
			module.parse(new File(getEglRule()));
		} catch (Exception e) {
			e.printStackTrace();
		}

		module.getContext().getModelRepository().addModel(m);

		try {
			out = (String) module.execute();
		} catch (EolRuntimeException e) {
			e.printStackTrace();
		}

		//Get Spatial Data
		t = getGeoData(t);

		//Save in DB
		t.setDateCreated(new Date());
		t.setTargetCode(out);
		transformationRepository.save(t);
		return out;
	}

	public String getTransformationCodeSEER2Relational(Transformation t) {
		String out = "";
		
		setBaseDirectory("c:\\transformation\\seer2relational\\");
		setSourceModelURI(getBaseDirectory()+t.getSourceName()+".eer");
		setTargetModelURI(getBaseDirectory()+t.getSourceName()+".relational");
		
		setSourceMetamodelURI(resourceLoader.getPath("transformation/seer2relational/eermm.ecore"));
		setTargetMetamodelURI(resourceLoader.getPath("transformation/seer2relational/relational.ecore"));
		setQvtRule(resourceLoader.getPath("transformation/seer2relational/seerToRelational.qvt"));
		setQvtRuleTransformation("eerTorelational");
		setQvtRuleDirection("rdbm");
		setEglRule(resourceLoader.getPath("transformation/seer2relational/seer2SQL.egl"));

		byte[] data = Base64.decodeBase64(t.getSourceData().getBytes());
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(new File(getSourceModelURI()));
			try {
				fos.write(data);
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

		// M2M
		initMediniQVT(System.out);
		launch();

		// M2T
		// Construct the EGL module
		EglTemplateFactoryModuleAdapter module =  
				new EglTemplateFactoryModuleAdapter(new EglFileGeneratingTemplateFactory());

		// Wrap it as an InMemoryEmfModel
		InMemoryEmfModel m = new InMemoryEmfModel("M", getTargetResource(), getTargetPackage());

		try {
			module.parse(new File(getEglRule()));
		} catch (Exception e) {
			e.printStackTrace();
		}

		module.getContext().getModelRepository().addModel(m);

		try {
			out = (String) module.execute();
		} catch (EolRuntimeException e) {
			e.printStackTrace();
		}

		//Get Spatial Data
		t = getGeoData(t);

		//Save in DB
		t.setDateCreated(new Date());
		t.setTargetCode(out);
		transformationRepository.save(t);
		return out;
	}
	
	
	public String getTransformationCodeRMM2Relational(Transformation t) {
		String out = "";
		
		setBaseDirectory("c:\\transformation\\rmm2relational\\"+t.getSourceName()+"\\");
		
		new File(getBaseDirectory()).mkdir();
		
		setSourceModelURI(getBaseDirectory()+t.getSourceName()+".rmm");
		setTargetModelURI(getBaseDirectory()+t.getSourceName()+".relational");
		
		setSourceMetamodelURI(resourceLoader.getPath("transformation/rmm2relational/rmm.ecore"));
		setTargetMetamodelURI(resourceLoader.getPath("transformation/rmm2relational/relational.ecore"));
		setQvtRule(resourceLoader.getPath("transformation/rmm2relational/rmmToRelational.qvt"));
		setQvtRuleTransformation("rmmToRelational");
		setQvtRuleDirection("target");
		setEglRule(resourceLoader.getPath("transformation/rmm2relational/rmm2SQL.egl"));

		
		byte[] data = Base64.decodeBase64(t.getSourceData().getBytes());
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(new File(getSourceModelURI()));
			try {
				fos.write(data);
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

		// M2M
		initMediniQVT(System.out);
		launch();

		// M2T
		// Construct the EGL module
		EglTemplateFactoryModuleAdapter module =  
				new EglTemplateFactoryModuleAdapter(new EglFileGeneratingTemplateFactory());

		// Wrap it as an InMemoryEmfModel
		InMemoryEmfModel m = new InMemoryEmfModel("M", getTargetResource(), getTargetPackage());

		try {
			module.parse(new File(getEglRule()));
		} catch (Exception e) {
			e.printStackTrace();
		}

		module.getContext().getModelRepository().addModel(m);

		try {
			out = (String) module.execute();
		} catch (EolRuntimeException e) {
			e.printStackTrace();
		}

		//Get Spatial Data
		t = getGeoData(t);

		//Save in DB
		t.setDateCreated(new Date());
		t.setTargetCode(out);
		transformationRepository.save(t);
		return out;
	}

	
	public Transformation getGeoData(Transformation t){
		if(t.getIpAddress() == null)
			return t;
		if(t.getIpAddress().equals("127.0.0.1") || t.getIpAddress().equals(""))
			return t;

		// A File object pointing to your GeoIP2 or GeoLite2 database
		File database = resourceLoader.getFile("geolite2/GeoLite2-City.mmdb");
		// This creates the DatabaseReader object, which should be reused across lookups.
		DatabaseReader reader = null;
		InetAddress ipAddress = null;
		CityResponse response = null;
		try {
			reader = new DatabaseReader.Builder(database).build();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			ipAddress = InetAddress.getByName(t.getIpAddress());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		// Replace "city" with the appropriate method for your database, e.g., "country".
		try {
			response = reader.city(ipAddress);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (GeoIp2Exception e) {
			e.printStackTrace();
		}
		if(response != null){

			Country country = response.getCountry();
			t.setCountryCode(country.getIsoCode());
			t.setCountryName(country.getName());

			Subdivision subdivision = response.getMostSpecificSubdivision();
			t.setRegion(subdivision.getName());
			t.setRegionName(subdivision.getIsoCode());

			City city = response.getCity();
			t.setCity(city.getName());

			Postal postal = response.getPostal();
			t.setPostalCode(postal.getCode());

			Location location = response.getLocation();
			t.setLatitude(location.getLatitude().toString());
			t.setLongitude(location.getLongitude().toString());
		}

		return t;
	}

	/**
	 * Initializes the QVT processor
	 * @param outputStream an {@link OutputStream} for logging
	 */
	public void initMediniQVT(OutputStream outputStream) {
		this.logger = new OutputStreamLog(outputStream);
		this.processorImpl = new EMFQvtProcessorImpl(this.logger);
		this.processorImpl.setProperty(QVTProcessorConsts.PROP_DEBUG, "true");
	}

	/**
	 * Provide the information about the metamodels, which are involved in the transformation
	 * @param ePackages the metamodel packages
	 */
	public void init(Collection<EPackage> ePackages) {
		Iterator<EPackage> iterator = ePackages.iterator();
		while (iterator.hasNext()) {
			this.processorImpl.addMetaModel(iterator.next());
		}
	}

	private void clean() {
		this.processorImpl.setModels(Collections.EMPTY_LIST);
	}

	/**
	 * Before transforming, the engine has to now the model to perform the transformation on, as
	 * well as a directory for the traces to store.
	 * @param modelResources models for the execution - take care of the right order!
	 * @param workingDirectory working directory of the QVT engine
	 */
	public void preExecution(Collection<?> modelResources, URI workingDirectory) {
		this.processorImpl.setWorkingLocation(workingDirectory);
		this.processorImpl.setModels(modelResources);
	}

	/**
	 * Transform a QVT script in a specific direction.
	 * @param qvtRuleSet the QVT transformation
	 * @param transformation name of the transformation
	 * @param direction name of the target - must conform to your QVT transformation definition
	 * @throws Throwable
	 */
	public void transform(Reader qvtRuleSet, String transformation, String direction) throws Throwable {
		this.processorImpl.evaluateQVT(qvtRuleSet, transformation, true, direction, new Object[0], null, this.logger);
		this.clean();
	}

	/**
	 * Helper for XMI loading. Does lazy loading.
	 * @param xmlFileName file name to load
	 * @return the EMF resource
	 */
	public Resource getResource(String xmlFileName) {
		URI uri = URI.createFileURI(xmlFileName);
		Resource resource = null;
		try {
			resource = this.resourceSet.getResource(uri, true);
		} catch (Throwable fileNotFoundException) {
			resource = this.resourceSet.createResource(uri);
		}
		return resource;
	}

	public void launch() {
		// initialize resource set of models
		this.resourceSet = new ResourceSetImpl();
		this.resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(
				Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());

		// collect all necessary packages from the metamodel(s)
		Collection<EPackage> metaPackages = new ArrayList<EPackage>();
		this.collectMetaModels(metaPackages);

		// make these packages known to the QVT engine
		this.init(metaPackages);

		// load the example model files - 
		Resource rootResource = this.getResource(getSourceModelURI());

		this.resourceSet.getResource(rootResource.getURI(), true);

		//Resource resource1 = getFirstSemanticModelResource(rootResource.getResourceSet());
		Resource resource1 = this.resourceSet.getResource(rootResource.getURI(), true);

		setTargetResource(this.getResource(getTargetModelURI()));

		// Collect the models, which should participate in the transformation.
		// You can provide a list of models for each direction.
		// The models must be added in the same order as defined in your transformation!
		Collection<Collection<Resource>> modelResources = new ArrayList<Collection<Resource>>();
		Collection<Resource> firstSetOfModels = new ArrayList<Resource>();
		Collection<Resource> secondSetOfModels = new ArrayList<Resource>();
		modelResources.add(firstSetOfModels);
		modelResources.add(secondSetOfModels);
		firstSetOfModels.add(resource1);
		secondSetOfModels.add(getTargetResource());

		// tell the QVT engine a directory to work in - e.g. to store the trace (meta)models
		URI directory = URI.createFileURI(getBaseDirectory());
		this.preExecution(modelResources, directory);

		// load the QVT relations
		FileReader qvtRuleSet = null;
		try {
			qvtRuleSet = new FileReader(getQvtRule()); // adjust the filename!
		} catch (FileNotFoundException fileNotFoundException) {
			fileNotFoundException.printStackTrace();
			return;
		}
		// tell the QVT engine, which transformation to execute - there might be more than one in
		// the QVT file!
		String transformation = getQvtRuleTransformation(); //adjust
		// give the direction of the transformation (according to the transformation definition)
		String direction = getQvtRuleDirection(); // adjust

		// just do it ;-)
		try {
			this.transform(qvtRuleSet, transformation, direction);
		} catch (Throwable throwable) {
			throwable.printStackTrace();
		}

		// Note: the engine does not save the model resources, which were participating in the
		// transformation.
		// You have to take care on this.
		try {
			getTargetResource().save(Collections.EMPTY_MAP);
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}

	/**
	 * Add all metamodel packages of models/qvt script
	 * @param metaPackages
	 * @return
	 */
	protected void collectMetaModels(Collection<EPackage> metaPackages) {

		// the resource set for the metamodels
		ResourceSetImpl mmResourceSet = new ResourceSetImpl();
		mmResourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(
				Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());

		// if we load the metamodels NOT into "resourceSet", then the traces cannot be loaded!
		EPackageImpl p = buildMetaModelFromEcoreFile( getSourceMetamodelURI(), this.resourceSet);

		this.targetPackage = buildMetaModelFromEcoreFile( getTargetMetamodelURI(), this.resourceSet);

		// make the metamodels known to the resourceset of the models
		this.resourceSet.getPackageRegistry().put(p.getNsURI(), p);
		this.resourceSet.getPackageRegistry().put(targetPackage.getNsURI(), targetPackage);
		// otherwise we would get
		// "(Relation 'RootBlock2RootBlock' initially has 0 tuple(s) to evaluate)" ..

		metaPackages.add(p);
		metaPackages.add(targetPackage);
	}

	/**
	 * Loads a metamodel into the given resource set
	 * @param path the path to the metamodel's ECORE file
	 * @param rset the resource set
	 * @return the root package of the metamodel
	 */
	public EPackageImpl buildMetaModelFromEcoreFile(String path, ResourceSet rset) {
		URI simpleMMFileUri;
		try {
			simpleMMFileUri = URI.createFileURI(path); // e.g. "c:\.."
		} catch (Exception e) {
			simpleMMFileUri = URI.createURI(path); // e.g. file:/c:/.. or
			// platform:/resource/MyProjectName/..
		}
		Resource simpleMMResource = rset.getResource(simpleMMFileUri, true);
		return (EPackageImpl) simpleMMResource.getContents().get(0);
	}

	public String getSourceModelURI() {
		return sourceModelURI;
	}

	public void setSourceModelURI(String sourceModelURI) {
		this.sourceModelURI = sourceModelURI;
	}

	public String getSourceMetamodelURI() {
		return sourceMetamodelURI;
	}

	public void setSourceMetamodelURI(String sourceMetamodelURI) {
		this.sourceMetamodelURI = sourceMetamodelURI;
	}

	public String getTargetModelURI() {
		return targetModelURI;
	}

	public void setTargetModelURI(String targetModelURI) {
		this.targetModelURI = targetModelURI;
	}

	public String getTargetMetamodelURI() {
		return targetMetamodelURI;
	}

	public void setTargetMetamodelURI(String targetMetamodelURI) {
		this.targetMetamodelURI = targetMetamodelURI;
	}

	public String getQvtRule() {
		return qvtRule;
	}

	public void setQvtRule(String qvtRule) {
		this.qvtRule = qvtRule;
	}

	public String getQvtRuleDirection() {
		return qvtRuleDirection;
	}

	public void setQvtRuleDirection(String qvtRuleDirection) {
		this.qvtRuleDirection = qvtRuleDirection;
	}

	public String getQvtRuleTransformation() {
		return qvtRuleTransformation;
	}

	public void setQvtRuleTransformation(String qvtRuleTransformation) {
		this.qvtRuleTransformation = qvtRuleTransformation;
	}

	public String getBaseDirectory() {
		return baseDirectory;
	}

	public void setBaseDirectory(String baseDirectory) {
		this.baseDirectory = baseDirectory;
	}

	public Resource getTargetResource() {
		return targetResource;
	}

	public void setTargetResource(Resource targetResource) {
		this.targetResource = targetResource;
	}

	public EPackageImpl getTargetPackage() {
		return targetPackage;
	}

	public void setTargetPackage(EPackageImpl targetPackage) {
		this.targetPackage = targetPackage;
	}

	public String getEglRule() {
		return eglRule;
	}

	public void setEglRule(String eglRule) {
		this.eglRule = eglRule;
	}

}

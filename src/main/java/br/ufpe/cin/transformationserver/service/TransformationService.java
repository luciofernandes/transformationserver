package br.ufpe.cin.transformationserver.service;

import br.ufpe.cin.transformationserver.domain.Transformation;

public interface TransformationService {
	public String getTransformationCodeEER2Relational(Transformation t);
	public String getTransformationCodeSEER2Relational(Transformation t);
	public String getTransformationCodeRMM2Relational(Transformation t);
}

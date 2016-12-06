package br.ufpe.cin.transformationserver.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.ufpe.cin.transformationserver.domain.Transformation;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value="classpath:config/applicationContext.xml")
public class TransformationServiceTest {

	@Autowired
	private TransformationService transformationService;
	
	@Test
	public void testGetTransformationCodeEER2Relational(){
		Transformation t = new Transformation();
		t.setUser("user.teste");
		t.setSourceData("PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiPz4NCjx4bWk6WE1JIHhtaTp2ZXJzaW9uPSIyLjAiIHhtbG5zOnhtaT0iaHR0cDovL3d3dy5vbWcub3JnL1hNSSIgeG1sbnM6ZWVyPSJlZXIiIHhtbG5zOm5vdGF0aW9uPSJodHRwOi8vd3d3LmVjbGlwc2Uub3JnL2dtZi9ydW50aW1lLzEuMC4yL25vdGF0aW9uIj4NCiAgPGVlcjpTY2hlbWEgeG1pOmlkPSJfWHNVbGtBZmlFZVd4WnRaVFJ2Xzc0QSI+DQogICAgPG5vZGVzIHhtaTp0eXBlPSJlZXI6RW50aXR5IiB4bWk6aWQ9Il9mUkdsb0FmaUVlV3hadFpUUnZfNzRBIiBuYW1lPSJCIiBBdHRyaWJ1dGVMaW5rU291cmNlPSJfZ1l4WVVBZmlFZVd4WnRaVFJ2Xzc0QSIvPg0KICAgIDxub2RlcyB4bWk6dHlwZT0iZWVyOkF0dHJpYnV0ZSIgeG1pOmlkPSJfZ1l0RzRBZmlFZVd4WnRaVFJ2Xzc0QSIgbmFtZT0iSWRCIiB0eXBlPSJJREVOVElGSUVSIiBkYXRhVHlwZT0iSU5URUdFUiIgQXR0cmlidXRlTGlua1RhcmdldD0iX2dZeFlVQWZpRWVXeFp0WlRSdl83NEEiLz4NCiAgICA8bGlua3MgeG1pOnR5cGU9ImVlcjpBdHRyaWJ1dGVMaW5rIiB4bWk6aWQ9Il9nWXhZVUFmaUVlV3hadFpUUnZfNzRBIiBzb3VyY2U9Il9mUkdsb0FmaUVlV3hadFpUUnZfNzRBIiB0YXJnZXQ9Il9nWXRHNEFmaUVlV3hadFpUUnZfNzRBIi8+DQogIDwvZWVyOlNjaGVtYT4NCiAgPG5vdGF0aW9uOkRpYWdyYW0geG1pOmlkPSJfWHN1MVFBZmlFZVd4WnRaVFJ2Xzc0QSIgdHlwZT0iRWVyIiBlbGVtZW50PSJfWHNVbGtBZmlFZVd4WnRaVFJ2Xzc0QSIgbmFtZT0iYXJ0aWdvX2NiaWUuZWVyIiBtZWFzdXJlbWVudFVuaXQ9IlBpeGVsIj4NCiAgICA8Y2hpbGRyZW4geG1pOnR5cGU9Im5vdGF0aW9uOk5vZGUiIHhtaTppZD0iX2ZSTUZNQWZpRWVXeFp0WlRSdl83NEEiIHR5cGU9IjIwMDEiIGVsZW1lbnQ9Il9mUkdsb0FmaUVlV3hadFpUUnZfNzRBIj4NCiAgICAgIDxjaGlsZHJlbiB4bWk6dHlwZT0ibm90YXRpb246RGVjb3JhdGlvbk5vZGUiIHhtaTppZD0iX2ZSTXNRQWZpRWVXeFp0WlRSdl83NEEiIHR5cGU9IjUwMDEiLz4NCiAgICAgIDxzdHlsZXMgeG1pOnR5cGU9Im5vdGF0aW9uOkRlc2NyaXB0aW9uU3R5bGUiIHhtaTppZD0iX2ZSTUZNUWZpRWVXeFp0WlRSdl83NEEiLz4NCiAgICAgIDxzdHlsZXMgeG1pOnR5cGU9Im5vdGF0aW9uOkZvbnRTdHlsZSIgeG1pOmlkPSJfZlJNRk1nZmlFZVd4WnRaVFJ2Xzc0QSIgZm9udE5hbWU9IlNlZ29lIFVJIi8+DQogICAgICA8c3R5bGVzIHhtaTp0eXBlPSJub3RhdGlvbjpGaWxsU3R5bGUiIHhtaTppZD0iX2ZSTUZNd2ZpRWVXeFp0WlRSdl83NEEiLz4NCiAgICAgIDxsYXlvdXRDb25zdHJhaW50IHhtaTp0eXBlPSJub3RhdGlvbjpCb3VuZHMiIHhtaTppZD0iX2ZSTUZOQWZpRWVXeFp0WlRSdl83NEEiIHg9Ijg1IiB5PSIxNjUiLz4NCiAgICA8L2NoaWxkcmVuPg0KICAgIDxjaGlsZHJlbiB4bWk6dHlwZT0ibm90YXRpb246Tm9kZSIgeG1pOmlkPSJfZ1l1VkFBZmlFZVd4WnRaVFJ2Xzc0QSIgdHlwZT0iMjAwNCIgZWxlbWVudD0iX2dZdEc0QWZpRWVXeFp0WlRSdl83NEEiPg0KICAgICAgPGNoaWxkcmVuIHhtaTp0eXBlPSJub3RhdGlvbjpEZWNvcmF0aW9uTm9kZSIgeG1pOmlkPSJfZ1l1VkJRZmlFZVd4WnRaVFJ2Xzc0QSIgdHlwZT0iNTAwNSIvPg0KICAgICAgPHN0eWxlcyB4bWk6dHlwZT0ibm90YXRpb246RGVzY3JpcHRpb25TdHlsZSIgeG1pOmlkPSJfZ1l1VkFRZmlFZVd4WnRaVFJ2Xzc0QSIvPg0KICAgICAgPHN0eWxlcyB4bWk6dHlwZT0ibm90YXRpb246Rm9udFN0eWxlIiB4bWk6aWQ9Il9nWXVWQWdmaUVlV3hadFpUUnZfNzRBIiBmb250TmFtZT0iU2Vnb2UgVUkiLz4NCiAgICAgIDxzdHlsZXMgeG1pOnR5cGU9Im5vdGF0aW9uOkZpbGxTdHlsZSIgeG1pOmlkPSJfZ1l1VkF3ZmlFZVd4WnRaVFJ2Xzc0QSIvPg0KICAgICAgPGxheW91dENvbnN0cmFpbnQgeG1pOnR5cGU9Im5vdGF0aW9uOkJvdW5kcyIgeG1pOmlkPSJfZ1l1VkJBZmlFZVd4WnRaVFJ2Xzc0QSIgeD0iNzAiIHk9Ijg1Ii8+DQogICAgPC9jaGlsZHJlbj4NCiAgICA8c3R5bGVzIHhtaTp0eXBlPSJub3RhdGlvbjpEaWFncmFtU3R5bGUiIHhtaTppZD0iX1hzdTFRUWZpRWVXeFp0WlRSdl83NEEiLz4NCiAgICA8ZWRnZXMgeG1pOnR5cGU9Im5vdGF0aW9uOkVkZ2UiIHhtaTppZD0iX2dZeW1jQWZpRWVXeFp0WlRSdl83NEEiIHR5cGU9IjQwMDciIGVsZW1lbnQ9Il9nWXhZVUFmaUVlV3hadFpUUnZfNzRBIiBzb3VyY2U9Il9mUk1GTUFmaUVlV3hadFpUUnZfNzRBIiB0YXJnZXQ9Il9nWXVWQUFmaUVlV3hadFpUUnZfNzRBIj4NCiAgICAgIDxzdHlsZXMgeG1pOnR5cGU9Im5vdGF0aW9uOlJvdXRpbmdTdHlsZSIgeG1pOmlkPSJfZ1l5bWNRZmlFZVd4WnRaVFJ2Xzc0QSIvPg0KICAgICAgPHN0eWxlcyB4bWk6dHlwZT0ibm90YXRpb246Rm9udFN0eWxlIiB4bWk6aWQ9Il9nWXltY2dmaUVlV3hadFpUUnZfNzRBIiBmb250TmFtZT0iU2Vnb2UgVUkiLz4NCiAgICAgIDxiZW5kcG9pbnRzIHhtaTp0eXBlPSJub3RhdGlvbjpSZWxhdGl2ZUJlbmRwb2ludHMiIHhtaTppZD0iX2dZeW1jd2ZpRWVXeFp0WlRSdl83NEEiIHBvaW50cz0iWy02LCAtMjAsIDQ5LCAxNzVdJFstNTUsIC0xOTUsIDAsIDBdIi8+DQogICAgPC9lZGdlcz4NCiAgPC9ub3RhdGlvbjpEaWFncmFtPg0KPC94bWk6WE1JPg0K");
		t.setSourceName("testeJunit");
		t.setType("EERToRelational");
		
		String out = transformationService.getTransformationCodeEER2Relational(t);
		
		assertEquals("-- #######################################################\n"
				+ "--  Feature in construction, not completely ready to use  \n"
				+ "--  Wait  for updates                                     \n"
				+ "--  Edson Alves - eas4@cin.ufpe.br                        \n"
				+ "-- #######################################################\n"
				+ "CREATE TABLE B( \n"
				+ "    IdB INTEGER,\n"
				+ "    PRIMARY KEY (IdB) \n"
				+ ");\n", out);
	}
	
}

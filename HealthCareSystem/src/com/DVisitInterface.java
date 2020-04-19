package com;

import model.DVisit;

public interface DVisitInterface {

	public DVisit DVisitInsert(DVisit visit);
	
	public String dVisitRead();
	
	public DVisit updateDVisit(DVisit visit);

	public String deleteDVisit(int id);

}
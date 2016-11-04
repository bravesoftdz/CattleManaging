package damdariar.gui.swing.forms;

import damdariar.beans.CCowLegStatus;
import damdariar.beans.CDaroConsume;
import damdariar.beans.CDelete;
import damdariar.beans.CExit;
import damdariar.beans.CFalh;
import damdariar.beans.CIll;
import damdariar.beans.CLegStatus;
import damdariar.beans.CLocationCow;
import damdariar.beans.CMilk;
import damdariar.beans.CNumber;
import damdariar.beans.CPic;
import damdariar.beans.CProperty;
import damdariar.beans.CPropertySine;
import damdariar.beans.CSonografi;
import damdariar.beans.CSpermCowS;
import damdariar.beans.CStatusCow;
import damdariar.beans.CTalghih;
import damdariar.beans.CTest;
import damdariar.beans.CZayeman;
import damdariar.beans.sematBase;

public class FormConfig {
	
	
	public static  String getComboBoxFilter(Class<?> clsType,String propertyName){
		if(clsType.equals(CProperty.class) &&  propertyName.equalsIgnoreCase("fatherId"))
			return " cowSex = ' \u0645\u0627\u062f\u0647' and active = 1 " ;
		else if(clsType.equals(CExit.class) && propertyName.equalsIgnoreCase("CPropertyId"))
			return "CProperty.active = 1";
		else if(clsType.equals(CStatusCow.class) && propertyName.equalsIgnoreCase("CPropertyId"))
			return "CProperty.active = 1";
		else if(clsType.equals(CNumber.class) && propertyName.equalsIgnoreCase("CPropertyId"))
			return "CProperty.active = 1";
		else if(clsType.equals(CMilk.class) && propertyName.equalsIgnoreCase("CPropertyId"))
			return "CProperty.active = 1 and CProperty.cowSex = ' \u0645\u0627\u062f\u0647'";
		else if(clsType.equals(CPic.class) && propertyName.equalsIgnoreCase("CPropertyId"))
			return "CProperty.active = 1";
		else if(clsType.equals(CDelete.class) && propertyName.equalsIgnoreCase("CPropertyId"))
			return "CProperty.active = 1";
		else if(clsType.equals(CZayeman.class) && propertyName.equalsIgnoreCase("CPropertyId"))
			return "CProperty.active = 1 and CProperty.cowSex = ' \u0645\u0627\u062f\u0647'";
		else if(clsType.equals(CLocationCow.class) && propertyName.equalsIgnoreCase("CPropertyId"))
			return "CProperty.active = 1";
		else if(clsType.equals(CLegStatus.class) && propertyName.equalsIgnoreCase("CPropertyId"))
			return "CProperty.active = 1";
		else if(clsType.equals(CPropertySine.class) && propertyName.equalsIgnoreCase("CPropertyId"))
			return "CProperty.active = 1 and CProperty.cowSex = ' \u0645\u0627\u062f\u0647'";
		else if(clsType.equals(CFalh.class) && propertyName.equalsIgnoreCase("CPropertyId"))
			return "CProperty.active = 1 and CProperty.cowSex = ' \u0645\u0627\u062f\u0647'";
		else if(clsType.equals(CSpermCowS.class) && propertyName.equalsIgnoreCase("CPropertyId"))
			return "CProperty.active = 1 and CProperty.cowSex = ' \u0645\u0627\u062f\u0647'";
		else if(clsType.equals(CTalghih.class) && propertyName.equalsIgnoreCase("CPropertyId"))
			return "CProperty.active = 1 and CProperty.cowSex = ' \u0645\u0627\u062f\u0647'";
		else if(clsType.equals(CTest.class) && propertyName.equalsIgnoreCase("CPropertyId"))
			return "CProperty.active = 1 and CProperty.cowSex = ' \u0645\u0627\u062f\u0647'";
		else if(clsType.equals(CCowLegStatus.class) && propertyName.equalsIgnoreCase("CPropertyId"))
			return "CProperty.active = 1 ";
		else if(clsType.equals(CIll.class) && propertyName.equalsIgnoreCase("CPropertyId"))
			return "CProperty.active = 1 ";
		else if(clsType.equals(CDaroConsume.class) && propertyName.equalsIgnoreCase("CPropertyId"))
			return "CProperty.active = 1 ";
		else if(clsType.equals(CSonografi.class) && propertyName.equalsIgnoreCase("CPropertyId"))
			return "CProperty.active = 1 and CProperty.cowSex = ' \u0645\u0627\u062f\u0647'";
		return null;
	}
	
	public static  String getGridFilter(Class<?> clsType){
		return null;
	}

}

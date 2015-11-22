package Utility;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class CheckUnits {

	/**
	 * @param args
	 * Author VidX
	 */
	public static void main(String[] args) {
		CheckUnits cu = new CheckUnits();
		cu.loadUnitTable();
		System.out.println(checkUnit("cm", "m", 100, 1));
	}
	
	public class unitds
	{
		String src;
		String dest;
		String formula;
		public String getSrc() {
			return src;
		}
		public void setSrc(String src) {
			this.src = src;
		}
		public String getDest() {
			return dest;
		}
		public void setDest(String dest) {
			this.dest = dest;
		}
		public String getFormula() {
			return formula;
		}
		public void setFormula(String formula) {
			this.formula = formula;
		}
	}
	
	void loadUnitTable()
	{
		char ch;
		StringBuffer src=new StringBuffer(""), dest=new StringBuffer(""), formula=new StringBuffer("");
		int addin=0;
		File f = new File("unitInput.txt");
		unitds ud = new unitds();
		try
		{
			FileInputStream fis = new FileInputStream(f);
			while((ch=(char)fis.read())!=(char)-1)
			{
				if(ch=='\n')
				{
					ud.setSrc(src.toString());
					ud.setDest(dest.toString());
					ud.setFormula(formula.toString());
					unitTable.add(ud);
					addin=0;
					src.delete(0, src.length());
					dest.delete(0, dest.length());
					formula.delete(0, formula.length());
				}
				else if(ch=='\t')
				{
					addin++;
				}
				else if(addin==0)
				{
					src.append(ch);
				}
				else if(addin==1)
				{
					dest.append(ch);
				}
				else if(addin==2)
				{
					formula.append(ch);
				}
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		System.out.println(unitTable.size());
		for(int i=0;i<unitTable.size();i++)
		{
			System.out.println(unitTable.get(i).getSrc()+" "+unitTable.get(i).getDest()+" "+unitTable.get(i).getFormula());
		}
	}
	
	static ArrayList<unitds> unitTable = new ArrayList<unitds>(); 
	
	public static int checkUnit(String src, String dest, double srcValue, double destValue)
	{
		for(int i=0;i<unitTable.size();i++)
		{
			if(unitTable.get(i).src.equals(src)&&unitTable.get(i).dest.equals(dest))
			{
				try
				{
					String input="";
					String tempFormula=unitTable.get(i).formula;
					input=tempFormula.replaceAll("src", Double.toString(destValue));
					//System.out.println("Hello Input here, i am having "+input);
					double answer;
					ScriptEngineManager mgr = new ScriptEngineManager();
				    ScriptEngine engine = mgr.getEngineByName("JavaScript");
				    
					answer=Double.parseDouble(engine.eval(input).toString());
					if(answer==srcValue)
					{
						return 1;
					}
					else
					{
						return -1;
					}
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}
		}
		return 0;
	}
}
package Graph;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.StringTokenizer;

public class CreateGraph {
	public void execute(String s,String filename) throws IOException
	{
				System.out.println("nishant hello");
				/*String s1="Has_Area_To_Be_Covered : TSA_Of_WoodenBox_SqCm(TSA_Of_Cuboid)\n"+
				"Has_Dimension_Of_Object_Used_For_Covering : Side_SquareSheet_Cm(Side)\n"
				+"Has_Object_To_Be_Covered : WoodenBox(Cuboid)\n"
				+"Has_Dimension_Of_Object_To_Be_Covered : LengthOfWoodenBox_Cm(Length)\n"
				+"Has_Dimension_Of_Object_To_Be_Covered : HeightOfWoodenBox_Cm(Height)\n"
				+"Has_Dimension_Of_Object_To_Be_Covered : Breadth_WoodenBox_Cm(Breadth)\n"
				+"Has_Number_of_Covering_Object : NumberOfSquareSheetsCoveringTheWooden_Box(NumberOfCoveringObjects)\n"
				+"Has_Object_Used_For_Covering : SquareShapedSheet(Square)";*/
				String schematic[];
				String semantic[];
				String linguistic[];
				schematic  =new String[50];
				semantic   =new String[50];
				linguistic =new String[50];
				String knwldg[]=new String[50];
				StringTokenizer st=new StringTokenizer(s,"\n");
				int i=0;
				while(st.hasMoreElements())
				{
					knwldg[i++]=""+st.nextElement();
				}
				for(int k=0;k<i;)
				{	
					StringTokenizer sst=new StringTokenizer(knwldg[k],":(");
				    while(sst.hasMoreElements())
				    {
				    	schematic[k]=""+sst.nextElement();
				    	semantic[k]=""+sst.nextElement();
				    	semantic[k]=semantic[k].trim();
				    	linguistic[k]=""+sst.nextElement();
				    	linguistic[k]= linguistic[k].substring(0, linguistic[k].length()-1);
				    	k++;
				    }
				}   
				for(int k=0;k<i;k++)
				{
					System.out.println(schematic[k]);
					System.out.println(semantic[k]);
					System.out.println(linguistic[k]);
				}
				File file = new File("/home/kumar/workspace/sampleV1/WebContent/"+filename+".json");
				if (!file.exists()) 
				{
					file.createNewFile();
				}
				FileWriter fw = new FileWriter(file.getAbsoluteFile());
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write("{\"nodes\" : [\n");
				
					bw.write("{\"name\": \"Problem\"},\n");
					String prvs="";
					for(int k=0;k<i;k++)
						if(!schematic[k].equals(prvs))
						{
							if(k!=0)
								bw.write(",\n");
							bw.write("{\"name\": \" "+schematic[k] +"\"}");
							prvs=schematic[k];
						}
					
					bw.write(",\n");
					for(int k=0;k<i;k++)
					{
						if(k!=0)
							bw.write(",\n");
						bw.write("{\"name\": \" "+semantic[k] +"\"}");
					}
					bw.write(",\n");
					for(int k=0;k<i;k++)
					{
						if(k!=0)
							bw.write(",\n");
						bw.write("{\"name\": \" "+linguistic[k] +"\"}");
					}	
					bw.write("\n],\n");					
					bw.write("\"edges\": [\n");
					prvs="";
					int temp=1;
					for(int k=0;k<i;k++)
					{
						if((!schematic[k].equals(prvs)))
						{
							if(k!=0)
								bw.write(",\n");
							bw.write("{\"source\": " +"0"+ ", \"target\": " + temp+ ", \"val\": \"" +"Schematic is"+"\" }");
							prvs=schematic[k];
							temp++;
						}						
					}	
					bw.write(",\n");
					int l=0;
					prvs="";
					for(int k=0;k<i;k++)
					{
						if((!schematic[k].equals(prvs)))
						{
							prvs=schematic[k];
							l++;
						}
						if(k!=0)
							bw.write(",\n");
						bw.write("{\"source\": " +l+ ", \"target\": " + temp+ ", \"val\": \"" +"Semantic is"+"\" }");						
						temp++;
					}
					bw.write(",\n");
					for(int k=0;k<i;k++)
					{
						l++;
						if(k!=0)
							bw.write(",\n");
						bw.write("{\"source\": " +l+ ", \"target\": " + temp+ ", \"val\": \"" +"Linguistic is"+"\" }");
						temp++;
					}
					bw.write("\n");
					bw.write("]}");
					bw.close();
					
			}
	}
						      
						      

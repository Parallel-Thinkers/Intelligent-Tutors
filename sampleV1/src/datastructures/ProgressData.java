package datastructures;

public class ProgressData {
	
	String problemtype;
	public String getProblemtype() {
		return problemtype;
	}
	public void setProblemtype(String problemtype) {
		this.problemtype = problemtype;
	}
	public int getEasy_attemps() {
		return easy_attemps;
	}
	public void setEasy_attemps(int easy_attemps) {
		this.easy_attemps = easy_attemps;
	}
	public double getEasy_correct() {
		return easy_correct;
	}
	public void setEasy_correct(double easy_correct) {
		this.easy_correct = easy_correct;
	}
	public int getMedium_attemps() {
		return medium_attemps;
	}
	public void setMedium_attemps(int medium_attemps) {
		this.medium_attemps = medium_attemps;
	}
	public double getMedium_correct() {
		return medium_correct;
	}
	public void setMedium_correct(double medium_correct) {
		this.medium_correct = medium_correct;
	}
	public int getHard_attemps() {
		return hard_attemps;
	}
	public void setHard_attemps(int hard_attemps) {
		this.hard_attemps = hard_attemps;
	}
	public double getHard_correct() {
		return hard_correct;
	}
	public void setHard_correct(double hard_correct) {
		this.hard_correct = hard_correct;
	}
	public double getOverall() {
		return overall;
	}
	public void setOverall(double overall) {
		this.overall = overall;
	}
	int easy_attemps = 0;
	double easy_correct=0.0;
	
	int medium_attemps = 0;
	double medium_correct=0.0;
	
	int hard_attemps = 0;
	double hard_correct=0.0;
	double overall=0.0;

}

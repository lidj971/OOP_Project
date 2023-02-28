package figures;

public class NegRadiusException extends Exception {
	public double radius;
	public NegRadiusException(double radius) 
	{
		this.radius = radius;
	}
}

package edu.iastate.cs228.hw01;

/**
 * @author Mason Walls
 */
import java.math.BigInteger;

@SuppressWarnings("serial")
public class Rational2 extends Number implements Comparable<Rational2> {
	// Data fields for numerator and denominator
	private BigInteger numerator = new BigInteger("0");
	private BigInteger denominator = new BigInteger("1");

	/** Default constructor */
	public Rational2() {
		this(new BigInteger("0"), new BigInteger("1"));
	}

	/** Construct a rational with specified numerator and denominator */
	public Rational2(BigInteger numerator, BigInteger denominator) {
		long gcd = gcd(numerator, denominator);
		this.numerator = new BigInteger(
				Long.toString(((denominator.longValue() > 0) ? 1 : -1) * numerator.longValue() / gcd));
		this.denominator = new BigInteger(Long.toString(Math.abs(denominator.longValue()) / gcd));
	}

	/** Find GCD of two numbers */
	private long gcd(BigInteger n, BigInteger d) {
		long n1 = Math.abs(n.intValue());
		long n2 = Math.abs(d.intValue());

		int gcd = 1;

		for (int k = 1; k <= n1 && k <= n2; k++) {
			if (n1 % k == 0 && n2 % k == 0)
				gcd = k;
		}
		return gcd;
	}

	/** Return numerator */
	public BigInteger getNumerator() {
		return numerator;
	}

	/** Return denominator */
	public BigInteger getDenominator() {
		return denominator;
	}

	/** Add a rational number to this rational */
	public Rational2 add(Rational2 secondRational) {
		BigInteger n = new BigInteger(Long.toString(numerator.intValue() * secondRational.getDenominator().intValue()
				+ denominator.intValue() * secondRational.getNumerator().intValue()));
		BigInteger d = new BigInteger(
				Long.toString(denominator.intValue() * secondRational.getDenominator().intValue()));
		return new Rational2(n, d);
	}

	/** Subtract a rational number from this rational */
	public Rational2 subtract(Rational2 secondRational) {
		BigInteger n = new BigInteger(Long.toString(numerator.intValue() * secondRational.getDenominator().intValue()
				- denominator.intValue() * secondRational.getNumerator().intValue()));
		BigInteger d = new BigInteger(
				Long.toString(denominator.intValue() * secondRational.getDenominator().intValue()));
		return new Rational2(n, d);
	}

	/** Multiply a rational number to this rational */
	public Rational2 multiply(Rational2 secondRational) {
		BigInteger n = new BigInteger(Long.toString(numerator.intValue() * secondRational.getNumerator().intValue()));
		BigInteger d = new BigInteger(
				Long.toString(denominator.intValue() * secondRational.getDenominator().intValue()));
		return new Rational2(n, d);
	}

	/** Divide a rational number from this rational */
	public Rational2 divide(Rational2 secondRational) {
		BigInteger n = new BigInteger(Long.toString(numerator.intValue() * secondRational.getDenominator().intValue()));
		BigInteger d = new BigInteger(Long.toString(denominator.intValue() * secondRational.numerator.intValue()));
		return new Rational2(n, d);
	}

	@Override
	public String toString() {
		if (denominator.intValue() == 1)
			return numerator.intValue() + "";
		else
			return numerator.intValue() + "/" + denominator.intValue();
	}

	/** Override the equals method in the Object class */
	public boolean equals(Object parm1) {
		if (this == parm1)
			return true;
		if ((parm1 == null) || (parm1.getClass() != this.getClass()))
			return false;

		if ((this.subtract((Rational2) (parm1))).getNumerator().intValue() == 0)
			return true;
		else
			return false;
	}

	/** Override the abstract intValue method in java.lang.Number */
	public int intValue() {
		return (int) doubleValue();
	}

	/** Override the abstract floatValue method in java.lang.Number */
	public float floatValue() {
		return (float) doubleValue();
	}

	/** Override the doubleValue method in java.lang.Number */
	public double doubleValue() {
		return numerator.intValue() * 1.0 / denominator.intValue();
	}

	/** Override the abstract longValue method in java.lang.Number */
	public long longValue() {
		return numerator.longValue() / denominator.longValue();
	}

	@Override
	public int compareTo(Rational2 o) {
		if (this.subtract(o).getNumerator().intValue() > 0)
			return 1;
		else if (this.subtract(o).getNumerator().intValue() < 0)
			return -1;
		else
			return 0;
	}
}

package BasicsTools;

import java.util.*;
import java.math.*;


public class Utils {
	// generate random prime number
	public static BigInteger randomPrimeGenerator(int n_bits) {
		BigInteger prime_number;
		do {
			prime_number = BigInteger.probablePrime(n_bits, new Random());
		} while (!prime_number.isProbablePrime(1));
		return prime_number;
	}

	// find the prime factors of given number
	public static Set<BigInteger> primeFactors(BigInteger number) {
		Set<BigInteger> factors = new HashSet<BigInteger>();
		while(number.mod(BigInteger.valueOf(2)).equals(BigInteger.ZERO)) {
			factors.add(BigInteger.valueOf(2));
			number = number.divide(BigInteger.valueOf(2));
		}
		BigInteger nextprime = BigInteger.valueOf(3);
		while (nextprime.pow(2).compareTo(number) <= 0) {
			while (number.mod(nextprime).equals(BigInteger.ZERO)){
				factors.add(nextprime);
				number = number.divide(nextprime);
			}
			nextprime = nextprime.nextProbablePrime();
		}
		if (number.compareTo(BigInteger.ONE) > 0 ) {
			factors.add(number);
		}
		return factors;
	}

	// calculate gcd(a,b)
	public static BigInteger recursiveGcd(BigInteger a, BigInteger b){
		if (b.equals(BigInteger.ZERO))
			return a;
		return recursiveGcd(b, a.mod(b));
	}

	//check be primitive root 
	public static Boolean checkPrimitive(BigInteger number, Set<BigInteger> factors,
		BigInteger phi, BigInteger prime) {
		for (BigInteger i : factors)
			if (number.modPow(phi.divide(i), prime).equals(BigInteger.ONE))
				return false;
		return true; 
	}

	// find primitive root
	public static BigInteger primitiveRoot(BigInteger prime) {
		BigInteger phi = prime.subtract(BigInteger.ONE);
		Set<BigInteger> primeFactors = primeFactors(phi);
		BigInteger generator = BigInteger.ONE;
		do {
			generator = generator.add(BigInteger.ONE);
		} while (!checkPrimitive(generator, primeFactors, phi, prime));
		return generator;
	}
}

package tdd;

import static org.junit.Assert.*;

import org.junit.Rule;

import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;
import org.junit.rules.ExpectedException;

public class KataTest 
{
	StringCalc s = new StringCalc();
	
	@Test
	public void EmptyStringTest() 
	{
		assertThat(s.add(""), is(0));
	}
	
	@Test
	public void OneNumberTest() 
	{
		assertThat(s.add("2"), is(2));
		assertThat(s.add("778"), is(778));
	}
	
	@Test
	public void MultipleNumberTest() 
	{
		assertThat(s.add("2,3"), is(5));
		assertThat(s.add("22,33"), is(55));
		assertThat(s.add("4,5,6"), is(15));
		assertThat(s.add("11,22,33"), is(66));
	}
	
	@Test
	public void NewLineTest() 
	{
		assertThat(s.add("2\n3"), is(5));
		assertThat(s.add("22\n33"), is(55));
		assertThat(s.add("1\n2,3"), is(6));
		assertThat(s.add("11,22\n33"), is(66));
	} 
	
	@Test
	public void DifferentDelimiterTest() 
	{
		assertThat(s.add("//;\n1;2"), is(3));
		assertThat(s.add("//;\n1;2;3"), is(6));
	} 
	
	@Rule
	public ExpectedException e = ExpectedException.none();
	
	@Test
	public void NegativeNumberTest() 
	{
		e.expect(IllegalArgumentException.class);
		e.expectMessage("Negative number not allowed -3");
		s.add("-3");
	} 
	
	@Test
	public void MultipleNegativeNumberTest() 
	{
		e.expect(IllegalArgumentException.class);
		e.expectMessage("Negative number not allowed -3 -5 ");
		s.add("-3,-5,15");
	} 
	
	@Test
	public void AddCounterTest() 
	{
		int add_ctr = s.getCalledCount();
		
		System.out.println("Number of times add is called " + add_ctr);
		
		assertThat(s.getCalledCount(), is(15));
	}   
	
	@Test
	public void GreaterThanThousandTest() 
	{
		assertThat(s.add("2,1001"), is(2));
	} 
	
	@Test
	public void GreaterLengthDelimTest() 
	{
		String numbers = "//[***]\n1***2***3";
		
		assertThat(s.add(numbers), is(6));
		//assertThat(s.add("//;\n1;2;3"), is(6));
	} 
	
	@Test
	public void MultipleDelimTest() 
	{
		String numbers = "//[*][%]\n1*2%3";
		
		assertThat(s.add(numbers), is(6));
	}
	
	@Test
	public void LongMultipleDelimTest() 
	{
		String numbers = "//[**][%%]\n1**2%%3";
		
		assertThat(s.add(numbers), is(6));
	}

}

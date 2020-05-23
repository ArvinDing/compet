package a;

import java.util.*;

public class AtomicMassTable {
	public HashMap<String, Double> table;

	public AtomicMassTable() {
		table = new HashMap<String, Double>();
		table.put("H", 1.00794);
		table.put("He", 4.0026);
		table.put("Li", 6.9412);
		table.put("Be", 9.0122);
		table.put("B", 10.8115);
		table.put("C", 12.011);
		table.put("N", 14.007);
		table.put("O", 15.9994);
		table.put("F", 18.998);
		table.put("Ne", 20.18);
		table.put("Na", 22.989768);
		table.put("Mg", 24.305);
		table.put("Al", 26.982);
		table.put("Si", 28.086);
		table.put("P", 30.974);
		table.put("S", 32.0666);
		table.put("Cl", 35.4527);
		table.put("Ar", 39.948);
		table.put("K", 39.098);
		table.put("Ca", 40.0784);
		table.put("Sc", 44.956);
		table.put("Ti", 47.883);
		table.put("V", 50.942);
		table.put("Cr", 51.996);
		table.put("Mn", 54.938);
		table.put("Fe", 55.4873);
		table.put("Co", 59.933);
		table.put("Ni", 58.693);
		table.put("Cu", 63.5463);
		table.put("Zn", 65.392);
		table.put("Ga", 69.723);
		table.put("Ge", 72.612);
		table.put("As", 74.922);
		table.put("Se", 78.963);
		table.put("Br", 79.904);
		table.put("Kr", 83.8);
		table.put("Rb", 85.468);
		table.put("Sr", 87.62);
		table.put("Y", 88.906);
		table.put("Zr", 91.2242);
		table.put("Nb", 92.906);
		table.put("Mo", 95.94);
		table.put("Tc", 98.906);
		table.put("Ru", 101.072);
		table.put("Rh", 102.91);
		table.put("Pd", 106.42);
		table.put("Ag", 107.87);
		table.put("Cd", 112.41);
		table.put("In", 114.82);
		table.put("Sn", 118.71);
		table.put("Sb", 121.763);
		table.put("Te", 127.6);
		table.put("I", 126.9);
		table.put("Xe", 131.292);
		table.put("Cs", 132.91);
		table.put("Ba", 137.33);
		table.put("La", 138.91);
		table.put("Ce", 140.12);
		table.put("Pr", 140.91);
		table.put("Nd", 144.243);
		table.put("Pm", 146.92);
		table.put("Sm", 150.363);
		table.put("Eu", 151.96);
		table.put("Gd", 157.253);
		table.put("Tb", 158.93);
		table.put("Dy", 162.503);
		table.put("Ho", 164.93);
		table.put("Er", 167.263);
		table.put("Tm", 168.93);
		table.put("Yb", 173.043);
		table.put("Lu", 174.97);
		table.put("Hf", 178.492);
		table.put("Ta", 180.95);
		table.put("W", 183.84);
		table.put("Re", 186.21);
		table.put("Os", 190.233);
		table.put("Ir", 192.223);
		table.put("Pt", 195.083);
		table.put("Au", 196.97);
		table.put("Hg", 200.592);
		table.put("Tl", 204.38);
		table.put("Pb", 207.2);
		table.put("Bi", 208.98);
		table.put("Po", 209.98);
		table.put("At", 209.99);
		table.put("Rn", 222.02);
		table.put("Fr", 223.02);
		table.put("Ra", 226.03);
		table.put("Ac", 227.03);
		table.put("Th", 232.04);
		table.put("Pa", 231.04);
		table.put("U", 238.03);
		table.put("Np", 237.05);
		table.put("Pu", 239.05);
		table.put("Am", 241.06);
		table.put("Cm", 244.06);
		table.put("Bk", 249.08);
		table.put("Cf", 252.08);
		table.put("Es", 252.08);
		table.put("Fm", 257.1);
		table.put("Md", 258.1);
		table.put("No", 259.1);
		table.put("Lr", 262.11);
	}

	public double get(String element) {
		return table.get(element);
	}

}
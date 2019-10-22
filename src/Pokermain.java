import java.util.Arrays;

public class Pokermain 
{
	static final int NUMBER_OF_CARDS=52; //final sorgt dafür dass Variablen als Konstante abgespeichert werden und später nicht mehr verändert werden können
	static int cardsnationwide[]= new int[NUMBER_OF_CARDS];
	static int HAND=5;
	static int han[] = new int[HAND];
	static int syms[] = new int[HAND];
	static int nums[] = new int[HAND];
	static final int anzahlVersuche=1000000;
	public static void main(String[] args) 
	{
		int amountPairs=0;
		double amount2Pairs=0;
		double amount3OfaKind=0;
		double amount4OfaKind=0;
		double amountFlush=0;
		double amountStreet=0;
		double amountStrFlush=0;
		double amountRoyalFlush=0;
		double amountFullHouse=0;

		for(int j=0;j<anzahlVersuche;j++)
		{
			for(int i=0;i<NUMBER_OF_CARDS;i++)
			{
				cardsnationwide[i]=i;
			}
			proof(HAND);
			for(int i=0;i<HAND;i++)
			{
				int u=han[i];
				calcSymbol(u,i);
				calcNumber(u,i);
			}
			Arrays.sort(nums);
			if (opair()){amountPairs++;}
			if (tpair()){amount2Pairs++;}
			if (toak()){amount3OfaKind++;}
			if (flush()){amountFlush++;}
			if (foak()){amount4OfaKind++;}
			if (street()){amountStreet++;}
			if (FullHouse()){amountFullHouse++;}
			if (StrFlush()){amountStrFlush++;}
			if (RoyalFlush()){amountRoyalFlush++;}	
		}
		
		//System.out.println("("+amountPairs+") Pair\n("+amount2Pairs+") 2 Pairs\n("+amount3OfaKind+") 3 Of a Kind\n("+amount4OfaKind+") 4 Of a Kind\n("+amountStreet+") Street\n("+amountFlush+") Flush\n("+amountStrFlush+") Straight Flush\n("+amountRoyalFlush+") Royal Flush");
		System.out.println(" Pair Wahrscheinlichkeit:\t\t"+(amountPairs*100.0)/anzahlVersuche+"\t42,26 %");
		System.out.println(" 2 Pairs Wahrscheinlichkeit:\t\t"+(amount2Pairs*100)/anzahlVersuche+"\t4,75 %");
		System.out.println(" 3 Of a Kind Wahrscheinlichkeit:\t"+(amount3OfaKind*100)/anzahlVersuche+"\t2,11 %");
		System.out.println(" 4 Of a Kind Wahrscheinlichkeit:\t"+(amount4OfaKind*100)/anzahlVersuche+"\t0,0240 %");
		System.out.println(" Flush Wahrscheinlichkeit:\t\t"+(amountFlush*100)/anzahlVersuche+"\t0,197 %");
		System.out.println(" Street Wahrscheinlichkeit:\t\t"+(amountStreet*100)/anzahlVersuche+"\t0,392 %");
		System.out.println(" Full House Wahrscheinlichkeit:\t\t"+(amountFullHouse*100/anzahlVersuche)+"\t0,144 %");
		System.out.println(" Straight Flush Wahrscheinlichkeit:\t"+(amountStrFlush*100)/anzahlVersuche+"\t0,00139 %");
		System.out.println(" Royal Flush Wahrscheinlichkeit:\t"+(amountRoyalFlush*100)/anzahlVersuche+"\t0,000154 %");
	}

	public static void proof(int HAND) 
	{
		for (int i=0;i<HAND;i++)
		{
			int z = (int)(Math.random() * (NUMBER_OF_CARDS-1-i));
			han[i]=cardsnationwide[z];
			cardsnationwide[z]=cardsnationwide[cardsnationwide.length-1-i];
			//System.out.println(han[i]);
		}
	}

	public static void calcNumber(int u, int i)
	{
		int n=u%13;
		nums[i]=n;
		//System.out.println(n);
	}

	public static void calcSymbol(int u, int i)
	{
		int f=u/13;
		syms[i]=f;
		//if(f==0)System.out.print("Heart\t\t");
		//if(f==1)System.out.print("diamonds\t");
		//if(f==2)System.out.print("clubs\t\t");
		//if(f==3)System.out.print("spades\t\t");
	}
	public static boolean opair()
	{
		for(int i=0;i<(nums.length-1);i++)
		{
			for(int j=i+1;j<nums.length;j++)
			{
				if(nums[i]==nums[j]) 
					return true;
			}
		}
		return false;
	}

	public static boolean tpair()
	{
		int w=0;
		for(int i=0;i<(HAND);i++)
		{
			for(int j=i;j<HAND;j++)
			{
				if(nums[i]==nums[j] && i!=j) 
				{
					w++;
				}
			}
		}
		if(w==2)
		{
			return true;
		}
		else return false;
	}

	public static boolean toak()
	{
		int w=0;
		for(int i=0;i<HAND;i++)
		{
			for(int j=i;j<HAND;j++)
			{
				if(nums[i]==nums[j] && i!=j) 
				{
					w++;
				}
			}
		}
		if(w==3)
		{
			return true;
		}
		return false;
	}

	public static boolean foak()
	{
		int w=0;
		for(int i=0;i<(HAND);i++)
		{
			for(int j=i;j<HAND;j++)
			{
				if(nums[i]==nums[j] && i!=j) 
				{
					w++;
				}
			}
		}
		if(w>=6)
		{
			return true;
		}
		return false;
	}
	
	public static boolean FullHouse()
	{
		if(tpair() && toak() && opair()) {return true;}
		return false;
	}

	public static boolean flush()
	{
		for(int i=0;i<(HAND-1);i++)
		{
			for(int j=i+1;j<HAND;j++)
			{
				if(syms[i]!=syms[j])
				{
					return false;
				}
			}
		}
		return true;
	}

	public static boolean street()
	{
		if((nums[0]%13)+1==(nums[1]%13) && (nums[1]%13)+1==(nums[2]%13) && (nums[2]%13)+1==(nums[3]%13) && (nums[3]%13)+1==(nums[4]%13))
		{
			return true;
		}
		return false;
	}

	public static boolean StrFlush()
	{
		if(street() && flush())
		{
			return true;
		}
		return false;
	}

	public static boolean RoyalFlush()
	{
		if(StrFlush() && nums[4]==12)
		{
			return true;
		}
		return false;
	}
}

package greedy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class StockBuyAndSell {

	public static void main(String[] args) {
		StockBuyAndSell obj = new StockBuyAndSell();
		
		List<StockPrice> stockPrices = new ArrayList<StockBuyAndSell.StockPrice>();
		StockPrice sp = obj.new StockPrice(44,new Date(1548688627000L));
		stockPrices.add(sp);
		sp = obj.new StockPrice(14,new Date(1548688627001L));
		stockPrices.add(sp);
		sp = obj.new StockPrice(55,new Date(1548688627002L));
		stockPrices.add(sp);
		sp = obj.new StockPrice(32,new Date(1548688627003L));
		stockPrices.add(sp);
		sp = obj.new StockPrice(66,new Date(1548688627004L));
		stockPrices.add(sp);
		sp = obj.new StockPrice(77,new Date(1548688627005L));
		stockPrices.add(sp);
		sp = obj.new StockPrice(56,new Date(1548688627006L));
		stockPrices.add(sp);
		sp = obj.new StockPrice(67,new Date(1548688627007L));
		stockPrices.add(sp);
		sp = obj.new StockPrice(78,new Date(1548688627008L));
		stockPrices.add(sp);
		sp = obj.new StockPrice(69,new Date(1548688627009L));
		stockPrices.add(sp);
		
		obj.printBuyAndSellDates(stockPrices);
		
	}
	
	class StockPrice{
		int price;
		Date date;
		
		StockPrice(int price, Date date){
			this.price = price;
			this.date = date;
		}
		
		
		
		public String toString() {
			return "[ "+date.getTime()+" : "+price+" ]";
		}
	}
	
	class PriceSorter implements Comparator<StockPrice>{

		@Override
		public int compare(StockPrice o1, StockPrice o2) {
			return o1.price < o2.price ? -1 : (o1.price == o2.price ? 0 : 1);
		}

	}
	
	public void printBuyAndSellDates(List<StockPrice> stockPrices) {
		Collections.sort(stockPrices, new PriceSorter());
		
		for(int i=0,j=stockPrices.size()-1; i<j;i++,j--) {
			StockPrice buy = stockPrices.get(i);
			StockPrice sell = stockPrices.get(j);
			
			if(buy.date.compareTo(sell.date) < 0) {
				System.out.println("Selected dates are - ");
				System.out.println(buy.toString());
				System.out.println(sell.toString());
				break;
			}
		}
	}
}

package java.test;

import java.math.BigDecimal;
import java.util.Date;

public class HashCodeTest {
	 private String symbol;
     private String exchange;
     private long lotSize;
     private int tickSize;
     private boolean isRestricted;
     private Date settlementDate;
     private BigDecimal price;
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((exchange == null) ? 0 : exchange.hashCode());
		result = prime * result + (isRestricted ? 1231 : 1237);
		result = prime * result + (int) (lotSize ^ (lotSize >>> 32));
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result
				+ ((settlementDate == null) ? 0 : settlementDate.hashCode());
		result = prime * result + ((symbol == null) ? 0 : symbol.hashCode());
		result = prime * result + tickSize;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HashCodeTest other = (HashCodeTest) obj;
		if (exchange == null) {
			if (other.exchange != null)
				return false;
		} else if (!exchange.equals(other.exchange))
			return false;
		if (isRestricted != other.isRestricted)
			return false;
		if (lotSize != other.lotSize)
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (settlementDate == null) {
			if (other.settlementDate != null)
				return false;
		} else if (!settlementDate.equals(other.settlementDate))
			return false;
		if (symbol == null) {
			if (other.symbol != null)
				return false;
		} else if (!symbol.equals(other.symbol))
			return false;
		if (tickSize != other.tickSize)
			return false;
		return true;
	}



}

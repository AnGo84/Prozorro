package ua.prozorro.exchangeRates;

import ua.prozorro.entity.PageElement;

import java.util.List;

public class ExchangeRatesNBUPage {
    private PageElement pageElement;
    private List<ExchangeRateNBU> exchangeRateNBUs;

    public PageElement getPageElement() {
        return pageElement;
    }

    public void setPageElement(PageElement pageElement) {
        this.pageElement = pageElement;
    }

    public List<ExchangeRateNBU> getExchangeRateNBUs() {
        return exchangeRateNBUs;
    }

    public void setExchangeRateNBUs(List<ExchangeRateNBU> exchangeRateNBUs) {
        this.exchangeRateNBUs = exchangeRateNBUs;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ExchangeRatesNBUPage{");
        sb.append("pageElement=").append(pageElement);
        sb.append(", exchangeRateNBUs=").append(exchangeRateNBUs);
        sb.append('}');
        return sb.toString();
    }
}

package ca.yorku.cmg.lob.stockexchange.tradingagent;

import ca.yorku.cmg.lob.stockexchange.StockExchange;
import ca.yorku.cmg.lob.stockexchange.events.NewsBoard;
import ca.yorku.cmg.lob.trader.Trader;

/**
 * Concrete factory to create TradingAgent instances.
 */
public class TradingAgentFactory extends AbstractTradingAgentFactory {
    @Override
    public TradingAgent createTradingAgent(String type, String style, Trader trader, StockExchange exchange, NewsBoard newsboard) {
        ITradingStrategy strategy;
        
        if ("Aggressive".equalsIgnoreCase(style)) {
            strategy = new AggressiveTradingStrategy();
        } else if ("Conservative".equalsIgnoreCase(style)) {
            strategy = new ConservativeTradingStrategy();
        } else {
            throw new IllegalArgumentException("Invalid trading style: " + style);
        }

        // Determine trading agent type
        if ("Institutional".equalsIgnoreCase(type)) {
            return new TradingAgentInstitutional(trader, exchange, newsboard, strategy);
        } else if ("Retail".equalsIgnoreCase(type)) {
            return new TradingAgentRetail(trader, exchange, newsboard, strategy);
        } else {
            throw new IllegalArgumentException("Invalid trading agent type: " + type);
        }
    }
}
package discordBot.commands.StockFetcher;

/**
 * Created by JohanvonHacht on 07/12/16.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StockFetcher {

    /*
    * Returns a Stock Object that contains info about a specified stock.
    * @param 	symbol the company's stock symbol
    * @return 	a stock object containing info about the company's stock
    * @see Stock
    */
    public static Stock getStock(String symbol) {
        String sym = symbol.toUpperCase();
        double price = 0.0;
        int volume = 0;
        double pe = 0.0;
        double eps = 0.0;
        double week52low = 0.0;
        double week52high = 0.0;
        double daylow = 0.0;
        double dayhigh = 0.0;
        double movingav50day = 0.0;
        double marketcap = 0.0;
        String name = "";
        String currency = "";
        double shortRatio = 0.0;
        double open = 0.0;
        double previousClose = 0.0;
        String exchange;
        double chg = 0.0;
        try {

            // Retrieve CSV File
            URL yahoo = new URL("http://finance.yahoo.com/d/quotes.csv?s="+ symbol + "&f=l1vc1r2ejkghm3j3nc4s7pox");
            URLConnection connection = yahoo.openConnection();
            InputStreamReader is = new InputStreamReader(connection.getInputStream());
            BufferedReader br = new BufferedReader(is);

            // Parse CSV Into Array
            String line = br.readLine();
            //Only split on commas that aren't in quotes
            String[] stockinfo = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

            // Handle Our Data
            StockHelper sh = new StockHelper();

            price = sh.handleDouble(stockinfo[0]);
            volume = sh.handleInt(stockinfo[1]);
            chg = sh.handleDouble(stockinfo[2]);
            pe = sh.handleDouble(stockinfo[3]);
            eps = sh.handleDouble(stockinfo[4]);
            week52low = sh.handleDouble(stockinfo[5]);
            week52high = sh.handleDouble(stockinfo[6]);
            daylow = sh.handleDouble(stockinfo[7]);
            dayhigh = sh.handleDouble(stockinfo[8]);
            movingav50day = sh.handleDouble(stockinfo[9]);
            marketcap = sh.handleDouble(stockinfo[10]);
            name = stockinfo[11].replace("\"", "");
            currency = stockinfo[12].replace("\"", "");
            shortRatio = sh.handleDouble(stockinfo[13]);
            previousClose = sh.handleDouble(stockinfo[14]);
            open = sh.handleDouble(stockinfo[15]);
            exchange = stockinfo[16].replace("\"", "");

        } catch (IOException e) {
            Logger log = Logger.getLogger(StockFetcher.class.getName());
            log.log(Level.SEVERE, e.toString(), e);
            return null;
        }

        return new Stock(sym, price, volume, pe, eps, week52low, week52high, daylow, dayhigh, movingav50day, marketcap, name,currency, shortRatio,previousClose,open,exchange,chg);

    }
}


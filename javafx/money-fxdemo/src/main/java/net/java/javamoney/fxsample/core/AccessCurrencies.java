package net.java.javamoney.fxsample.core;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.Locale;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import javax.money.CurrencyUnit;
import javax.money.LocalizableCurrencyUnit;
import javax.money.MonetaryAmount;
import javax.money.provider.Monetary;

import net.java.javamoney.fxsample.core.CreateAmounts.ExamplePane;
import net.java.javamoney.fxsample.widgets.AbstractExamplePane;
import net.java.javamoney.fxsample.widgets.AbstractSingleSamplePane;
import net.java.javamoney.fxsample.widgets.AmountEntry;
import net.java.javamoney.fxsample.widgets.CurrencySelector;

public class AccessCurrencies extends AbstractExamplePane {

	public AccessCurrencies() {
		super(new CreateCurrencyPane());
		setExampleTitle("Accessing Currencies");
		setExampleDescription("This example shows how to access currencies of different namespaces and times.");
		setExampleCode(loadExample("/samples/CreateCurrencies.javatxt"));
	}

	public final static class CreateCurrencyPane extends
			AbstractSingleSamplePane {

		private HBox exPane = new HBox();

		private CurrencySelector selector = new CurrencySelector("Select Currency");

		public CreateCurrencyPane() {
			exPane.getChildren().add(selector);
			this.inputPane.getChildren().add(exPane);
			AnchorPane.setLeftAnchor(exPane, 10d);
			AnchorPane.setTopAnchor(exPane, 10d);
			Button actionButton = new Button("Access Currency");
			actionButton.setOnAction(new javafx.event.EventHandler<ActionEvent>() {
				public void handle(ActionEvent action) {
					StringWriter sw = new StringWriter();
					PrintWriter pw = new PrintWriter(sw);
					try {
						CurrencyUnit currency = selector.getCurrency();
						pw.println("CurrencyUnit");
						pw.println("------------");
						pw.println();
						printSummary(currency, pw);
					} catch (Exception e) {
						e.printStackTrace(pw);
					}
					pw.flush();
					CreateCurrencyPane.this.outputArea.setText(sw.toString());
				}

				private void printSummary(CurrencyUnit currency, PrintWriter pw) {
					pw.println("Class: " + currency.getClass().getName());
					pw.println("Namespace: " + currency.getNamespace());
					pw.println("Currency Code: " + currency.getCurrencyCode());
					pw.println("Numeric Code: " + currency.getNumericCode());
					pw.println("Legal Tender: " + currency.isLegalTender());
					pw.println("Virtual Currency: " + currency.isVirtual());
					pw.println("Default Fraction Digits: " + currency.getDefaultFractionDigits());
					if(currency instanceof LocalizableCurrencyUnit){
						LocalizableCurrencyUnit lcu = (LocalizableCurrencyUnit)currency;
						pw.println("LocalizableCurrencyUnit: true");
						pw.println("Display Name: " + lcu.getDisplayName(Locale.ENGLISH));
						pw.println("Symbol: " + lcu.getSymbol(Locale.ENGLISH));

					}
					else{
						pw.println("LocalizableCurrencyUnit: true");
					}
				}
			});
			buttonPane.getChildren().add(actionButton);
		}
	}
}

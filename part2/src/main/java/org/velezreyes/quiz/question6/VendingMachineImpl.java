package org.velezreyes.quiz.question6;

import java.util.HashMap;
import java.util.Map;

public class VendingMachineImpl implements VendingMachine {
  private Map<String, Drink> drinks;
  private int balance;

  private static VendingMachine instance;

  private VendingMachineImpl() {
    drinks = new HashMap<>();
    balance = 0;

    // Add available drinks
    drinks.put("ScottCola", new DrinkImpl("ScottCola", true, 75));
    drinks.put("KarenTea", new DrinkImpl("KarenTea", false, 100));
  }

  public static VendingMachine getInstance() {
    if (instance == null) {
      instance = new VendingMachineImpl();
    }
    return instance;
  }

  @Override
  public void insertQuarter() {
    balance += 25;
  }

  @Override
  public Drink pressButton(String name) throws NotEnoughMoneyException, UnknownDrinkException {
    Drink drink = drinks.get(name);

    if (drink == null) {
      throw new UnknownDrinkException();
    }

    if (balance < drink.getPrice()) {
      throw new NotEnoughMoneyException();
    }

    balance -= drink.getPrice();
    return drink;
  }

  private class DrinkImpl implements Drink {
    private String name;
    private boolean fizzy;
    private int price;

    DrinkImpl(String name, boolean fizzy, int price) {
      this.name = name;
      this.fizzy = fizzy;
      this.price = price;
    }

    @Override
    public String getName() {
      return name;
    }

    @Override
    public boolean isFizzy() {
      return fizzy;
    }

    @Override
    public int getPrice() {
      return price;
    }
  }
}

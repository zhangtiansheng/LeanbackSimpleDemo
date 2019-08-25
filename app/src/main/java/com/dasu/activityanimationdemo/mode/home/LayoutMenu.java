package com.dasu.activityanimationdemo.mode.home;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class LayoutMenu {

    private long menuId;
    private String menuName;
    private List<Element> elementList;

    public long getMenuId() {
        return menuId;
    }

    public LayoutMenu setMenuId(long menuId) {
        this.menuId = menuId;
        return this;
    }

    public String getMenuName() {
        return menuName;
    }

    public LayoutMenu setMenuName(String menuName) {
        this.menuName = menuName;
        return this;
    }

    public List<Element> getElementList() {
        return elementList == null ? (new ArrayList<Element>()) : elementList;
    }

    public LayoutMenu setElementList(List<Element> elementList) {
        this.elementList = elementList;
        return this;
    }

    @Override
    public String toString() {
        return "LayoutMenu{" +
                "menuId=" + menuId +
                ", menuName='" + menuName + '\'' +
                ", elementList=" + elementList +
                '}';
    }

    public static class Element implements Serializable {
        private List<CardElement> cardList;

        public List<CardElement> getCardList() {
            return cardList;
        }

        public Element setCardList(List<CardElement> cardList) {
            this.cardList = cardList;
            return this;
        }

        @Override
        public String toString() {
            return "Element{" +
                    "cardList=" + cardList +
                    '}';
        }
    }

    public static class CardElement implements Serializable {
        private long elementId;
        private String elementName;
        private String url;

        public long getElementId() {
            return elementId;
        }

        public CardElement setElementId(long elementId) {
            this.elementId = elementId;
            return this;
        }

        public String getElementName() {
            return elementName;
        }

        public CardElement setElementName(String elementName) {
            this.elementName = elementName;
            return this;
        }

        public String getUrl() {
            return url;
        }

        public CardElement setUrl(String url) {
            this.url = url;
            return this;
        }

        @Override
        public String toString() {
            return "CardElement{" +
                    "elementId=" + elementId +
                    ", elementName='" + elementName + '\'' +
                    '}';
        }
    }

}

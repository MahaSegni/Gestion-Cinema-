/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.utils;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author khalil
 */
public class Paginator {
    private Form form;
    private List<Container> elements;
    private List<Button> buttonList;
    private Container allContainer;
    private Container currentPage;
    private Container pageSelection;
    private Container pageNumberContainer;
    private Container infos;
    private Container pageNumberC;
    private Label infosLabel;
    private Label pageNumber;
    private int currentPageIndex;
    private int elementsPerPage;
    private int nbPages;
    
    public Paginator(Form f) {
        form=f;
        allContainer=new Container(BoxLayout.y());
        elements=new ArrayList<>();
        buttonList=new ArrayList<>();
        currentPage=new Container(BoxLayout.y());
        pageSelection=new Container(BoxLayout.xCenter());
        pageNumberContainer=new Container(BoxLayout.x());
        infos=new Container(BoxLayout.x());
        infosLabel=new Label();
        ComboBox<Integer> elementsPerPageC=new ComboBox<>();
        elementsPerPageC.addItem(5);
        elementsPerPageC.addItem(10);
        elementsPerPageC.addItem(15);
        elementsPerPageC.addItem(20);
        elementsPerPageC.addItem(25);
        elementsPerPageC.addActionListener(l->{elementsPerPage=elementsPerPageC.getSelectedItem();currentPageIndex=1;refresh();});
        infos.addAll(infosLabel, elementsPerPageC);
        pageNumber=new Label("Page 1");
        pageNumberC=new Container(BoxLayout.xCenter());
        pageNumberC.add(pageNumber);
        allContainer.addAll(currentPage, infos, pageNumberC, pageSelection);
        currentPageIndex=1;
        elementsPerPage=5;
        
        
    }
    
    public Container getAllInOneContainer() {
        return allContainer;
    }
    
    public Container getPageContainer() {
        return currentPage;
    }
    
    public Container getInfosContainer() {
        return infos;
    }
    
    public Container getPageSelectionContainer() {
        return pageSelection;
    }
    
    public Container getPageNumberContainer() {
        return pageNumberC;
    }

    public int getCurrentPageIndex() {
        return currentPageIndex;
    }

    public void setCurrentPageIndex(int currentPageIndex) {
        if (currentPageIndex>=1 && currentPageIndex<=nbPages) {
            this.currentPageIndex = currentPageIndex;
            refresh();
        }
    }

    public int getElementsPerPage() {
        return elementsPerPage;
    }

    public void setElementsPerPage(int elementsPerPage) {
        if (elementsPerPage>=1) {
            this.elementsPerPage = elementsPerPage;
            refresh();
        }
    }

    public int getNbPages() {
        return nbPages;
    }
    
    public void add(Container element) {
        element.getAllStyles().setBgColor(0x99CCCC);
        element.getAllStyles().setBgTransparency(40);
        element.getAllStyles().setMarginBottom(50);
        elements.add(element);
        refresh();
        
    }
    
    public void remove(Container element) {
        elements.remove(element);
        refresh();
    }
    
    public void clear() {
        elements.clear();
        refresh();
    }
    
    private void refreshPage() {
        currentPage.removeAll();
        for (int i=(currentPageIndex-1)*elementsPerPage;i<elementsPerPage*currentPageIndex;i++) {
            if (i>=elements.size())
                break;
            currentPage.add(elements.get(i));
        }
    }
    
    private void refreshPageSelection() {
        buttonList.clear();
        pageSelection.removeAll();
        nbPages=(int)Math.ceil((double)elements.size()/(double)elementsPerPage);
        if (nbPages > 1) {
            Button previousPage=new Button();
            FontImage.setMaterialIcon(previousPage, FontImage.MATERIAL_ARROW_LEFT);
            previousPage.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent l) {
                    if (currentPageIndex>1) {
                        currentPageIndex--;
                        refresh();
                    }
                }
            });
            pageSelection.add(previousPage);
            for (int i=1;i<=nbPages;i++) {
                final int wantedPage=i;
                Button pageIndexButton=new Button(Integer.toString(i));
                pageIndexButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent l) {
                        if (currentPageIndex!=wantedPage) {
                            currentPageIndex=wantedPage;
                            refresh();
                        }
                    }
                });
                buttonList.add(pageIndexButton);
            }
            pageSelection.add(pageNumberContainer);
            Button nextPage=new Button();
            FontImage.setMaterialIcon(nextPage, FontImage.MATERIAL_ARROW_RIGHT);
            nextPage.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent l) {
                    if (currentPageIndex<nbPages) {
                        currentPageIndex++;
                        refreshPage();
                        refreshInfos();
                        refreshSelectionStyle();
                    }
                }
            });
            pageSelection.add(nextPage);
            refreshSelectionStyle();
        }
    }
    
    private void refreshInfos() {
        int sup=elementsPerPage*currentPageIndex;
        if (sup > elements.size())
            sup=elements.size();
        infosLabel.setText((((currentPageIndex-1)*elementsPerPage)+1)+" à "+sup+" éléments sur "+elements.size()+"   Par:");
        pageNumber.setText("Page "+currentPageIndex);
    }
    
    private void refreshSelectionStyle() {
        pageNumberContainer.removeAll();
        if (buttonList.size()<=8) {
            for (Button b : buttonList)
                pageNumberContainer.add(b);
        } else {
            if (currentPageIndex<5) {
                for (int i=0;i<5;i++)
                    pageNumberContainer.add(buttonList.get(i));
                pageNumberContainer.add(new Label("..."));
                pageNumberContainer.add(buttonList.get(buttonList.size()-1));
            } else if (currentPageIndex>buttonList.size()-4) {
                pageNumberContainer.add(buttonList.get(0));
                pageNumberContainer.add(new Label("..."));
                for (int i=buttonList.size()-5;i<buttonList.size();i++)
                    pageNumberContainer.add(buttonList.get(i));
            } else {
                pageNumberContainer.add(buttonList.get(0));
                pageNumberContainer.add(new Label("..."));
                for (int i=currentPageIndex-2;i<=currentPageIndex;i++)
                    pageNumberContainer.add(buttonList.get(i));
                pageNumberContainer.add(new Label("..."));
                pageNumberContainer.add(buttonList.get(buttonList.size()-1));
            }
        }
        form.refreshTheme();
    }
    
    private void refresh() {
        refreshPage();
        refreshInfos();
        refreshPageSelection();
        refreshSelectionStyle();
       
        
    }
}

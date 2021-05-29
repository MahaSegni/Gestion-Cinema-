/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author bhk
 */
public class HomeFormH extends BaseForm {

    Form current;

    /*Garder traçe de la Form en cours pour la passer en paramètres 
    aux interfaces suivantes pour pouvoir y revenir plus tard en utilisant
    la méthode showBack*/
    public HomeFormH(Resources res) {
        current = this; //Récupération de l'interface(Form) en cours
        setTitle("Home");
//        setLayout(BoxLayout.y());
//
//        add(new Label("Choose an option"));
//        Button btnAddTask = new Button("Add Task");
//        Button btnListTasks = new Button("List Tasks");
//
////        btnAddTask.addActionListener(e -> new AddTaskForm(current).show());
//        btnAddTask.addActionListener(e -> new EmployeL().show());
//        //btnListTasks.addActionListener(e -> new ListTasksForm(current).show());
//        addAll(btnAddTask, btnListTasks);

        addMenu(this);

    }

    public static void addMenu(Form f) {
        Resources res = null;

        f.getToolbar().addMaterialCommandToLeftSideMenu("Accueil", " ".charAt(0), e -> new HomeFormH(res).show());
        f.getToolbar().addMaterialCommandToLeftSideMenu("Liste Employe", " ".charAt(0), e -> new EmployeL().show());
        f.getToolbar().addMaterialCommandToLeftSideMenu("Ajouter Employe", " ".charAt(0), e -> new EmployeA().show());
        f.getToolbar().addMaterialCommandToLeftSideMenu("Ajouter Conge", " ".charAt(0), e -> new CongeA().show());
        f.getToolbar().addMaterialCommandToLeftSideMenu("Liste Conge", " ".charAt(0), e -> new CongeL().show());
    }

}

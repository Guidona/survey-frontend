package com.frontend.survey.views.list;

import com.frontend.survey.data.Questionnaire;
import com.frontend.survey.service.SurveyProxy;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Page;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;

@PageTitle("list")
@Route(value = "")
public class ListView extends VerticalLayout {

    public ListView() {
        setSpacing(false);

        Image img = new Image("images/empty-plant.png", "placeholder plant");
        img.setWidth("50px");
        // add(img);

        H2 header = new H2("Application de gestion des questionnaires");
        header.addClassNames(Margin.Top.LARGE, Margin.Bottom.MEDIUM);
        add(header);
        add(new Paragraph("Exporter les reponses de votre questionnaire"));

        Button export = new Button();
        export.setText("Export");

        Select<Questionnaire> questionnaires = new Select<>();

        questionnaires.setLabel("Selectionnez le questionnaire conserne");
        questionnaires.setItems(SurveyProxy.getListQuestionnaires());
        questionnaires.setPlaceholder("Questionnaire pour exportation...");
        questionnaires.setWidth("500px");

        HorizontalLayout exportInput = new HorizontalLayout(
                questionnaires);

        exportInput.setAlignItems(Alignment.BASELINE);

        questionnaires.addValueChangeListener(event -> {
            System.out.println(questionnaires.getValue());
            exportInput.add(export);
        });
        export.addClickListener(event -> {
            UI.getCurrent().getPage().open(SurveyProxy.getDownloadLink(questionnaires.getValue().getId()));
        });

        add(exportInput);

        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("text-align", "center");
    }

}

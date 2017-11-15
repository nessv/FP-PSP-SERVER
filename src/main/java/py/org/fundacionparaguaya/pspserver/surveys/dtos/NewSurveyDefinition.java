package py.org.fundacionparaguaya.pspserver.surveys.dtos;

/*
 * FP-PSP Server
 * A sample API to manage surveys
 *
 * OpenAPI spec version: 1.0.0
 *
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * NewSurveyDefinition
 */

public class NewSurveyDefinition  implements StoreableDefinition {

    private String title;

    private String description;

    @JsonProperty("survey_schema")
    private SurveySchema surveySchema = new SurveySchema();

    @JsonProperty("survey_ui_schema")
    private SurveyUISchema surveyUISchema = new SurveyUISchema();

    public NewSurveyDefinition() {
    }

    public NewSurveyDefinition surveySchema(SurveySchema surveySchema) {
        this.surveySchema = surveySchema;
        return this;
    }

    /**
     * Get surveySchema
     * @return surveySchema
     **/
    @JsonProperty("survey_schema")
    @ApiModelProperty(required = true, value = "The schema of this survey. For each key/value pair, defines the type and other attributes")
    @NotNull
    public SurveySchema getSurveySchema() {
        return surveySchema;
    }

    public void setSurveySchema(SurveySchema surveySchema) {
        this.surveySchema = surveySchema;
    }

    public NewSurveyDefinition surveyUiSchema(SurveyUISchema surveyUiSchema) {
        this.surveyUISchema = surveyUiSchema;
        return this;
    }

    /**
     * The UI SCHEMA definition of the survey, to be used on render
     * @return surveyUiSchema
     **/
    @JsonProperty("survey_ui_schema")
    @ApiModelProperty(required = true, value = "The UI schema of this survey. Similar to survey_schema, this property describes the attributes of this survey that should be taken into consideration when rendering this survey.")
    @NotNull
    @Override
    public SurveyUISchema getSurveyUISchema() {
        return surveyUISchema;
    }

    public void setSurveyUISchema(SurveyUISchema surveyUISchema) {
        this.surveyUISchema = surveyUISchema;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        NewSurveyDefinition newSurveyDefinition = (NewSurveyDefinition) o;
        return Objects.equals(this.surveySchema, newSurveyDefinition.surveySchema) &&
                Objects.equals(this.surveyUISchema, newSurveyDefinition.surveyUISchema);
    }

    @Override
    public int hashCode() {
        return Objects.hash(surveySchema, surveyUISchema);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class NewSurveyDefinition {\n");

        sb.append("    surveySchema: ").append(toIndentedString(surveySchema)).append("\n");
        sb.append("    surveyUiSchema: ").append(toIndentedString(surveyUISchema)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

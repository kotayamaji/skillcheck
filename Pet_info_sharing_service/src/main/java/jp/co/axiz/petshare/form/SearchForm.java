package jp.co.axiz.petshare.form;

/*
 * 検索画面用フォーム
 */
public class SearchForm {
    private String name;
    private Integer typeId;
    private String typeName;

    public String getName() {
        return name;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

}

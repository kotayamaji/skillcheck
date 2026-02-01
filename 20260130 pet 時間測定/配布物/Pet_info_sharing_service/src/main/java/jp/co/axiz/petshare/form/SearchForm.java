package jp.co.axiz.petshare.form;

/*
 * 検索画面用フォーム
 */
public class SearchForm {

    private String name;
    private Integer Id;
    private String animalTypeName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer animaltypeId) {
        this.Id = animaltypeId;
    }

    public String getAnimalTypeName() {
        return animalTypeName;
    }

    public void setAnimalTypeName(String animalTypeName) {
        this.animalTypeName = animalTypeName;
    }

}

package py.org.fundacionparaguaya.pspserver.families.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.google.common.base.MoreObjects;

import py.org.fundacionparaguaya.pspserver.common.entities.BaseEntity;
import py.org.fundacionparaguaya.pspserver.system.entities.CityEntity;
import py.org.fundacionparaguaya.pspserver.families.constants.Gender;
import py.org.fundacionparaguaya.pspserver.system.entities.CountryEntity;

@Entity
@Table(name = "person", schema = "ps_families")
public class PersonEntity extends BaseEntity {

	private static final long serialVersionUID = 1762584396723284335L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="ps_families.person_person_id_seq")
    @SequenceGenerator(name="ps_families.person_person_id_seq", sequenceName="ps_families.person_person_id_seq", allocationSize=1)
	@Column(name = "person_id")
	private Long personId;
	
	private String name;
	
	private String lastname;
	
	private String identificationType;
	
	private String identificationNumber;
	
	private String personRole;
	
	@Column(name = "gender")
	@NotNull
	@Enumerated(EnumType.STRING)
	private Gender gender;
	
	private String activityPrimary;
	
	private String activitySecundary;
	
	private String phoneNumber;
	
	@ManyToOne(targetEntity = CountryEntity.class)
	@JoinColumn(name = "country")
	private CountryEntity country;
	
	@ManyToOne(targetEntity = CityEntity.class)
	@JoinColumn(name = "city")
	private CityEntity city;
	
	@ManyToOne(targetEntity = FamilyEntity.class)
	@JoinColumn(name = "family_id")
	private FamilyEntity family;
	
	public FamilyEntity getFamily() {
		return family;
	}

	public void setFamily(FamilyEntity family) {
		this.family = family;
	}

	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getIdentificationType() {
		return identificationType;
	}

	public void setIdentificationType(String identificationType) {
		this.identificationType = identificationType;
	}

	public String getIdentificationNumber() {
		return identificationNumber;
	}

	public void setIdentificationNumber(String identificationNumber) {
		this.identificationNumber = identificationNumber;
	}

	public String getPersonRole() {
		return personRole;
	}

	public void setPersonRole(String personRole) {
		this.personRole = personRole;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getActivityPrimary() {
		return activityPrimary;
	}

	public void setActivityPrimary(String activityPrimary) {
		this.activityPrimary = activityPrimary;
	}

	public String getActivitySecundary() {
		return activitySecundary;
	}

	public void setActivitySecundary(String activitySecundary) {
		this.activitySecundary = activitySecundary;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public CountryEntity getCountry() {
		return country;
	}

	public void setCountry(CountryEntity country) {
		this.country = country;
	}

	public CityEntity getCity() {
		return city;
	}

	public void setCity(CityEntity city) {
		this.city = city;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (personId == null || obj == null || getClass() != obj.getClass())
			return false;
		PersonEntity toCompare = (PersonEntity) obj;
		return personId.equals(toCompare.personId);
	}
	
	@Override
	public int hashCode() {
		return personId == null ? 0 : personId.hashCode();
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
				.add("personId", personId)
				.add("name", name)
				.add("lastname", lastname)
				.add("identificationType", identificationType)
				.add("identificationNumber", identificationNumber)
				.add("personRole", personRole)
				.add("gender", gender)
				.add("activityPrimary", activityPrimary)
				.add("activitySecundary", activitySecundary)
				.add("phoneNumber", phoneNumber)
				.add("country", country.toString())
				.add("city", city.toString())
				.add("family", family.toString())
				.toString();
	}
	
}

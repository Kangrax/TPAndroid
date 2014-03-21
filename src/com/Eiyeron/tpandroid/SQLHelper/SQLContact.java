package com.Eiyeron.tpandroid.SQLHelper;

public class SQLContact {
	private long id;
	private String first_name;
	private String last_name;
	private String phone_number;
	private String mail;
	private String label;
	private String address;
	private String picture;

	public SQLContact(long id, String first_name, String last_name,
			String phone_number, String mail, String address, String label, String picture) {
		super();
		this.id = id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.phone_number = phone_number;
		this.mail = mail;
		this.address=address;
		this.label=label;
		this.picture=picture;
	}

	public long getId()
	{
		return id;
	}
	
	public void setId(long id)
	{
		this.id=id;
	}
	
	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getMail() {
		return mail;
	}
	
	public void setMail(String mail)
	{
		this.mail=mail;
	}
	
	public String getAddress()
	{
		return this.address;
	}
	
	public void setAdress( String adress)
	{
		this.address=address;
	}
	
	public String getLabel() {
		return label;
	}
	
	public void setLabel(String label)
	{
		this.label=label;
	}
	
	public String getPicture()
	{
		return this.picture;
	}
	
	public void setPicture(String picture)
	{
		this.picture=picture;
	}
	
}

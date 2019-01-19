package autoconf.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;


@Entity
@Table(name="vendor")
public class Vendor{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String name;
	
	@Column(name="lines_count")
	private Integer linesCount;
	
	public Vendor() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getLinesCount() {
		return linesCount;
	}

	public void setLinesCount(Integer linesCount) {
		this.linesCount = linesCount;
	}
	
	
	
	
}

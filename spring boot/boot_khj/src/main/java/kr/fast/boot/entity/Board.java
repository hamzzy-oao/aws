package kr.fast.boot.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name ="board")
@Getter 
@NoArgsConstructor 
@AllArgsConstructor 
public class Board {
	
	@Id
	// 기본키 생성을 DB가 생성함. 보통 기본키가 auto_increment일 때 사용
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false)
	private String name;
	
	public Board(String name) {
		this.name = name;
	}

	public void update(String name) {
		this.name = name;
		
	}


	

}

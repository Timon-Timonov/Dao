package dto;

import annotations.AutoIncrement;
import annotations.Column;
import annotations.PrimaryKey;
import annotations.Table;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@ToString

@Table(name = dto.ConstDto.TABLE_NAME_PERSON)
public class Person {


	@PrimaryKey
	@AutoIncrement
	@Column(name = dto.ConstDto.ID)
	private int id;

	@Column(name = dto.ConstDto.SURNAME)
	private String surname;

	@Column(name = dto.ConstDto.NAME)
	private String name;
}

package gov.wisconsin.framework.generate;

import java.util.Random;

public class JPAGenerator {

	private static final Random rand = new Random();
	
	public static void main(String[] args) {
		generate("admin", "T024_Category");
	}
	
	public static void generate(String project, String tableName) {
		StringBuilder pkBuilder = new StringBuilder();
		StringBuilder daoBuilder = new StringBuilder();
		StringBuilder cargoBuilder = new StringBuilder();
		
		/** DAO **/
		daoBuilder.append("\npackage gov.wisconsin." + project + ".data.dao;");
		daoBuilder.append("\n");
		daoBuilder.append("\nimport gov.wisconsin.framework.data.FwDAO;");
		daoBuilder.append("\n");
		daoBuilder.append("\nimport gov.wisconsin." + project + ".data.pojo." + tableName + "_PK;");
		daoBuilder.append("\nimport gov.wisconsin." + project + ".data.pojo." + tableName + "_Cargo;");
		daoBuilder.append("\n");
		daoBuilder.append("\nimport org.springframework.stereotype.Repository;");
		daoBuilder.append("\nimport org.springframework.data.jpa.repository.JpaRepository;");
		daoBuilder.append("\n");
		daoBuilder.append("\n@Repository");
		daoBuilder.append("\npublic interface " + tableName + "_DAO extends JpaRepository<" + tableName + "_Cargo, " + tableName + "_PK>, FwDAO {}");
		
		/** PK **/
		pkBuilder.append("\npackage gov.wisconsin." + project + ".data.pojo;");
		pkBuilder.append("\n");
		pkBuilder.append("\nimport lombok.Data;");
		pkBuilder.append("\n");
		pkBuilder.append("\nimport javax.persistence.Embeddable;");
		pkBuilder.append("\nimport javax.validation.constraints.NotNull;");
		pkBuilder.append("\n");
		pkBuilder.append("\nimport gov.wisconsin.framework.data.FwPK;");
		pkBuilder.append("\n");
		pkBuilder.append("\n@Embeddable @Data");
		pkBuilder.append("\npublic class " + tableName + "_PK implements FwPK {");
		pkBuilder.append("\n\tprivate static final long serialVersionUID = " + rand.nextLong() + "L;");
		pkBuilder.append("\n");
		pkBuilder.append("\n\tpublic " + tableName + "_PK() {}");
		pkBuilder.append("\n");
		pkBuilder.append("\n}");
		
		/** CARGO **/
		cargoBuilder.append("\npackage gov.wisconsin." + project + ".data.pojo;");
		cargoBuilder.append("\n");
		cargoBuilder.append("\nimport lombok.Data;");
		cargoBuilder.append("\n");
		cargoBuilder.append("\nimport javax.persistence.Id;");
		cargoBuilder.append("\nimport javax.persistence.Table;");
		cargoBuilder.append("\nimport javax.persistence.Entity;");
		cargoBuilder.append("\nimport javax.persistence.Embedded;");
		cargoBuilder.append("\n");
		cargoBuilder.append("\nimport gov.wisconsin.framework.data.FwCargo;");
		cargoBuilder.append("\n");
		cargoBuilder.append("\n@Entity @Data");
		cargoBuilder.append("\n@Table(name = \"" + tableName + "\")");
		cargoBuilder.append("\npublic class " + tableName + "_Cargo implements FwCargo {");
		cargoBuilder.append("\n");
		cargoBuilder.append("\n\t@Id");
		cargoBuilder.append("\n\t@Embedded");
		cargoBuilder.append("\n\tprivate " + tableName + "_PK PK;");
		cargoBuilder.append("\n");
		cargoBuilder.append("\n\tpublic " + tableName + "_Cargo() {}");
		cargoBuilder.append("\n");
		cargoBuilder.append("\n}");
		
		System.out.println("--------------- PK ---------------");
		System.out.println(pkBuilder.toString() + "\n");
		
		System.out.println("--------------- DAO ---------------");
		System.out.println(daoBuilder.toString() + "\n");
		
		System.out.println("--------------- CARGO ---------------");
		System.out.println(cargoBuilder.toString() + "\n");
	}
}

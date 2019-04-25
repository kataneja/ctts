package gov.wisconsin.framework.generate;

public class RequestResponseGenerator {
	
	public static void main(String[] args) {
		generate("admin", "Category");
	}
	
	public static void generate(String project, String apiName) {
		StringBuilder requestBuilder = new StringBuilder();
		StringBuilder responseBuilder = new StringBuilder();
		
		/** REQUEST **/
		requestBuilder.append("\npackage gov.wisconsin." + project + ".transport.request;");
		requestBuilder.append("\n");
		requestBuilder.append("\nimport lombok.Data;");
		requestBuilder.append("\nimport lombok.NoArgsConstructor;");
		requestBuilder.append("\nimport lombok.AllArgsConstructor;");
		requestBuilder.append("\n");
		requestBuilder.append("\nimport gov.wisconsin.framework.transport.FwRequest;");
		requestBuilder.append("\n");
		requestBuilder.append("\n@Data @NoArgsConstructor @AllArgsConstructor");
		requestBuilder.append("\npublic class " + apiName + "Request extends FwRequest {");
		requestBuilder.append("\n");
		requestBuilder.append("\n}");
		
		/** RESPONSE **/
		responseBuilder.append("\npackage gov.wisconsin." + project + ".transport.response;");
		responseBuilder.append("\n");
		responseBuilder.append("\nimport lombok.Data;");
		responseBuilder.append("\nimport lombok.NoArgsConstructor;");
		responseBuilder.append("\nimport lombok.AllArgsConstructor;");
		responseBuilder.append("\n");
		responseBuilder.append("\nimport gov.wisconsin.framework.transport.FwResponse;");
		responseBuilder.append("\n");
		responseBuilder.append("\n@Data @NoArgsConstructor @AllArgsConstructor");
		responseBuilder.append("\npublic class " + apiName + "Response extends FwResponse {");
		responseBuilder.append("\n");
		responseBuilder.append("\n}");
		
		System.out.println("--------------- REQUEST ---------------");
		System.out.println(requestBuilder.toString() + "\n");
		
		System.out.println("--------------- RESPONSE ---------------");
		System.out.println(responseBuilder.toString() + "\n");
	}
}

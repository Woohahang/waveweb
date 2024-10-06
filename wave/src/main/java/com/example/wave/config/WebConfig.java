package com.example.wave.config;

//import java.io.File;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class WebConfig implements WebMvcConfigurer {
//
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/api/**")
//                .allowedOrigins("http://localhost:3000") // React 앱의 URL
//                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
//    }
//    
//	@Override
//	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		// 현재 작업 디렉토리 출력
//		System.out.println("Current working directory: " + System.getProperty("user.dir"));
//
//		// 파일 리스트 출력
//		File buildDir = new File("../../frontend");
//		if (buildDir.exists() && buildDir.isDirectory()) {
//			String[] files = buildDir.list();
//			if (files != null) {
//				System.out.println("Files in build directory:");
//				for (String file : files) {
//					System.out.println(file);
//				}
//			} else {
//				System.out.println("No files found in the build directory.");
//			}
//		} else {
//			System.out.println("Build directory does not exist.");
//		}
//        
//        
////        registry.addResourceHandler("/**")
////                .addResourceLocations("file:../frontend/build/"); // react-folder를 실제 React 프로젝트 경로로 변경
//        
//        
//
//    }
//}

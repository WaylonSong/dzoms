package com.dz.common.other;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jxls.area.Area;
import org.jxls.builder.AreaBuilder;
import org.jxls.builder.xls.XlsCommentAreaBuilder;
import org.jxls.common.CellRef;
import org.jxls.common.Context;
import org.jxls.expression.JexlExpressionEvaluator;
import org.jxls.formula.FastFormulaProcessor;
import org.jxls.transform.Transformer;
import org.jxls.util.TransformerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.dz.common.global.BaseAction;

@Controller
@Scope("prototype")
public class ExcelOutputUtil extends BaseAction {
	private List<List<? extends Serializable>> datalist;
	private List<String> datasrc;
	private String templatePath;
	private InputStream excelStream;
	private String outputName;
	
	public String export(){
		if(datasrc==null||datasrc.size()==0){
			datasrc=(List<String>) request.getAttribute("datasrc");
		}
		
		if(datalist==null||datalist.size()==0){
			datalist=(List<List<? extends Serializable>>) request.getAttribute("datalist");
		}
		
		try(InputStream is = new FileInputStream(System.getProperty("com.dz.root")+templatePath)) {
			File file = File.createTempFile(outputName, "xls");
	        
			try (OutputStream os = new FileOutputStream(file)) {
	            Context context = new Context();
	            
//	            System.out.println(datasrc);
//	            System.out.println(datalist);
	            for (int i = 0; i < datasrc.size(); i++) {
	            	context.putVar(datasrc.get(i), (List)datalist.get(i));
				}
//	            System.out.println(context);
	            
	            AreaBuilder areaBuilder = new XlsCommentAreaBuilder();
	            
	            Transformer transformer = TransformerFactory.createTransformer(is, os);
	            JexlExpressionEvaluator evaluator = (JexlExpressionEvaluator) transformer.getTransformationConfig().getExpressionEvaluator();
	            Map<String, Object> functionMap = new HashMap<>();
	            functionMap.put("my", new MyELFunctionExtend());
	            evaluator.getJexlEngine().setFunctions(functionMap);
	           
	            areaBuilder.setTransformer(transformer);
	            List<Area> xlsAreaList = areaBuilder.build();
	            for (Area xlsArea : xlsAreaList) {
	                xlsArea.applyAt(
	                        new CellRef(xlsArea.getStartCellRef().getCellName()), context);
	                
	                setFormulaProcessor(xlsArea);
	                xlsArea.processFormulas();
	            }
	            transformer.write();
	            
	            this.setExcelStream(new FileInputStream(file));
	            
	            this.outputName = new String(outputName.getBytes(),"iso8859-1");
	        } catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
	    } catch (FileNotFoundException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		return SUCCESS;
	}
	
	 private void setFormulaProcessor(Area xlsArea) {
	     xlsArea.setFormulaProcessor(new FastFormulaProcessor());
	    // xlsArea.setFormulaProcessor(new StandardFormulaProcessor());
	 }

	public List getDatalist() {
		return datalist;
	}

	public void setDatalist(List datalist) {
		this.datalist = datalist;
	}

	public List<String> getDatasrc() {
		return datasrc;
	}

	public void setDatasrc(List<String> datasrc) {
		this.datasrc = datasrc;
	}

	public String getTemplatePath() {
		return templatePath;
	}

	public void setTemplatePath(String templatePath) {
		this.templatePath = templatePath;
	}

	public InputStream getExcelStream() {
		return excelStream;
	}

	public void setExcelStream(InputStream excelStream) {
		this.excelStream = excelStream;
	}

	public String getOutputName() {
		return outputName;
	}

	public void setOutputName(String outputName) {
		this.outputName = outputName;
	}
	
	public static class MyELFunctionExtend{
		public Object getObject(String classname,Serializable id){
			return ObjectAccess.getObject(classname, id);
		}
		
		private int seq=0;
		public int initSeq(){
			seq=0;
			return seq;
		}
		
		public int geneSeq(int step){
			seq+=step;
			return seq;
		}
		
	}
}


<h1>Generic Excel Exporter</h1>

<p>This project shows implementation a generic excel exporter developed in Java using Spring REST.</p>

<br/>

<aside>

<h3>ExcelExporterService</h3>

<p>
This service works with the <strong>HttpServletResponse</strong>, <strong>filename</strong> and the <strong>items</strong>.
</p>

```java
@GetMapping("/export/products")
public void exportProductsToExcel(HttpServletResponse response) throws IOException, IllegalAccessException {

  var products = dataService.findAllProduct();

  ExcelExporterService excelExporterService = new ExcelExporterService();

  excelExporterService.export(response, "Produtos", products);
}
```
<a  target="_blank"  rel="noopener noreferrer"  href="https://github.com/mcosta21/excel-exporter-spring/blob/main/src/main/java/com/mcosta21/excelexporterspring/controller/ExcelController.java">View code</a>

</aside>
  

<aside>

<h3>@ExportableField annotation</h3>

<p>
This annotation is used for filter all class attributes that will be export.
</p>

```java
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExportableField {

  public String name() default "";
  
}
```

<a  target="_blank"  rel="noopener noreferrer"  href="https://github.com/mcosta21/excel-exporter-spring/blob/main/src/main/java/com/mcosta21/excelexporterspring/model/ExportableField.java">View code</a>

</aside>

  

<aside>

<h3>Product Class</h3>

<p>

All attributes with <strong>@ExportableField</strong> annotation will be exportable, and value passed on <strong>name</strong> attribute will appear on Excel file, if doesn't setting, the default value shown will be the variable name.

</p>

```java
public class Product {

  @ExportableField(name = "Código")
  private Long code;
  
  @ExportableField(name = "Nome do produto")
  private String description;
  
  @ExportableField(name = "Preço")
  private Double price = 0.0;
  
  @ExportableField(name = "Categoria")
  private Category category;
  
  @ExportableField()
  private Stock stock;
  
}
```

<a  target="_blank"  rel="noopener noreferrer"  href="https://github.com/mcosta21/excel-exporter-spring/blob/main/src/main/java/com/mcosta21/excelexporterspring/model/Product.java">View code</a>

  
<aside>

<h3>Product Excel File</h3>

<div align="center">
  <img alt="" src="https://github.com/mcosta21/excel-exporter-java/blob/main/src/main/resources/static/excel.png" />
</div>

</aside>
  
 <aside>

<h3>Dependencies</h3>

   - Java 11
   - Apache POI 5.0.0

</aside>

  

<br/>

<div align="center">
💪 Contributions are welcome!
</div>
<br/>
<div align="center">
Developed by <a  target="_blank"  rel="noopener noreferrer"  href="https://github.com/mcosta21">Marcio Costa</a>
</div>

</footer>

  

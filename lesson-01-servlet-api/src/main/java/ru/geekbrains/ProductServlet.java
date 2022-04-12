package ru.geekbrains;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/product/*")
public class ProductServlet extends HttpServlet {

    private ProductRepository productRepository;

    @Override
    public void init() throws ServletException {
        this.productRepository = new ProductRepository();
        this.productRepository.insert(new Product("HDD", 2500));
        this.productRepository.insert(new Product("SSD", 3500));
        this.productRepository.insert(new Product("Ryzen3", 12000));
        this.productRepository.insert(new Product("Ryzen5", 25000));
        this.productRepository.insert(new Product("Ryzen7", 35000));
        this.productRepository.insert(new Product("Ryzen9", 120000));
        this.productRepository.insert(new Product("Flash Disk", 1500));
        this.productRepository.insert(new Product("Flash microSD", 1000));
        this.productRepository.insert(new Product("LCD монитор", 12000));
        this.productRepository.insert(new Product("GeForce RTX2070", 75000));
        this.productRepository.insert(new Product("Mouse", 350));
        this.productRepository.insert(new Product("Keyboard", 1000));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter wr = resp.getWriter();
        if (req.getParameter("id") == null) {
            wr.println("<table>");
            wr.println("<tr>");
            wr.println("<th>Id</th>");
            wr.println("<th>Title</th>");
            wr.println("<th>Price</th>");
            wr.println("</tr>");
            for (Product product : productRepository.findAll()) {
                wr.println("<tr>");
                wr.println("<td><a href='?id=" + product.getId() + "'>" + product.getId() + "</a></td>");
                wr.println("<td>" + product.getTitle() + "</td>");
                wr.println("<td>" + product.getCost() + "</td>");
                wr.println("</tr>");
            }
            wr.println("</table>");
        } else {
            wr.println("<h1>Информация о выбранном продукте</h1>");
            wr.println("<h3>Продукт: "+productRepository.findById(Long.parseLong(req.getParameter("id"))).getTitle()+"</h3>");
            wr.println("<h3>Стоимость: "+productRepository.findById(Long.parseLong(req.getParameter("id"))).getCost()+"</h3>");
        }
    }
}


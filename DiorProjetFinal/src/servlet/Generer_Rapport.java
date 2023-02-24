package servlet;

import java.io.IOException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.List;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.io.font.FontProgram;
import com.itextpdf.io.font.FontProgramFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;

import dao.ArticleDAO;
import models.ArticlesStock;



@WebServlet("/Generer_Rapport")
public class Generer_Rapport extends HttpServlet{
	
	private static final long serialVersionUID = 866318785181733691L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String stockPath = request.getServletContext().getRealPath( "/report/Rapport_stock.pdf" );
        response.setContentType( "application/pdf" );
        
        try ( PdfReader reader = new PdfReader( stockPath ); 
        	  PdfWriter writer = new PdfWriter( response.getOutputStream() ); 
        	  PdfDocument document = new PdfDocument( reader, writer ) ) {
        	
        	PdfPage page = document.getPage( 1 );
            PdfCanvas canvas = new PdfCanvas( page );
            
            FontProgram fontProgram = FontProgramFactory.createFont();
            PdfFont font = PdfFontFactory.createFont( fontProgram, "utf-8" );
            canvas.setFontAndSize( font, 12 );
            
            canvas.beginText();
            NumberFormat formatter = NumberFormat.getNumberInstance( new Locale( "fr", "FR" ) );
            
            Date date = new Date();
            SimpleDateFormat dateFormatComp;
            
            dateFormatComp = new SimpleDateFormat("dd MMM yyyy hh:mm:ss a");
            canvas.setTextMatrix( 430, 737 );
            canvas.showText(dateFormatComp.format(date));
            
            
            canvas.setTextMatrix( 460	, 717 );
            canvas.showText( formatter.format( 1) );


            
            int top = 640;
            double totalPrice = 0;
            int totalArt = 0;
            
            ArticleDAO dao = new ArticleDAO();

			List<ArticlesStock> produitsList = dao.ListArticle();
            



            for (ArticlesStock line : produitsList) {
            	
                ArticlesStock article = line;
                
                canvas.setTextMatrix( 45, top );
                canvas.showText( "" + article.getCodeArt() );

                canvas.setTextMatrix( 113, top );
                canvas.showText( formatter.format( article.getQteArt() ) );

                canvas.setTextMatrix( 160, top );
                canvas.showText( article.getNomArt() );

                canvas.setTextMatrix( 270, top );
                canvas.showText( article.getDescArt() );

                canvas.setTextMatrix( 442	, top );
                canvas.showText( formatter.format( article.getPrixArt() ) );
                
                double Prixtotale = article.getPrixArt() * article.getQteArt();
                totalPrice += Prixtotale;
                canvas.setTextMatrix( 492, top );
                canvas.showText( formatter.format( Prixtotale ) );
                
                totalArt++;
                top -= 15;
            }
            
            canvas.setTextMatrix( 450, 132 );
            canvas.showText( formatter.format( totalPrice ) );
            
            canvas.setTextMatrix( 180, 132 );
            canvas.showText( formatter.format( totalArt ) );
            

            canvas.endText();
        }
	}

}
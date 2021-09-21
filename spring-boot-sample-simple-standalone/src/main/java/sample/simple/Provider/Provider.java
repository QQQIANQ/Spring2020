package sample.simple.Provider;


import org.springframework.stereotype.Component;
import sample.simple.Article.Article;
import sample.simple.Article.IArticle;

import java.util.List;



@Component
public class Provider implements IProvider{
	
    @Override
    public double getPrice(Article article) {
        return 0;
    }

    @Override
    public void order(Article article, int quantity) {
    }

}

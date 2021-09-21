package sample.aop.Provider;


import org.springframework.stereotype.Component;
import sample.aop.Article.Article;

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

package sample.simple.Provider;


import org.springframework.stereotype.Component;
import sample.simple.Article.Article;

import java.util.List;

@Component
public class Provider implements IProvider{
    @Override
    public double getPrice(Article article) {
        return 0;
    }

    @Override
    public void order(List<Article> panier, Article article) {
            panier.add(article);
    }

}

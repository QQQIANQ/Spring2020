package sample.simple.Provider;

import org.springframework.stereotype.Component;
import sample.simple.Article.Article;

import java.util.List;

public interface IProvider {

    double getPrice(Article article);
    void order(List<Article> panier, Article article);
}

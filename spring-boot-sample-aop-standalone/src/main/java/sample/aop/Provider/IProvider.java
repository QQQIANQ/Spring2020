package sample.aop.Provider;

import org.springframework.stereotype.Component;
import sample.aop.Article.Article;

import java.util.List;

public interface IProvider {

    double getPrice(Article article);
    void order(List<Article> panier, Article article);
}

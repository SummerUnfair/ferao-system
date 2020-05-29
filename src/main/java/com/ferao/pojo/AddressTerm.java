package com.ferao.pojo;/*
 * @author Ferao
 * @date
 * @discription
 */
import org.elasticsearch.action.admin.indices.analyze.AnalyzeResponse;

public class AddressTerm {

    public String term;
    public int startOffset;
    public int endOffset;
    public String type;

    public AddressTerm(AnalyzeResponse.AnalyzeToken token)
    {
        this.term = token.getTerm();
        this.startOffset = token.getStartOffset();
        this.endOffset = token.getEndOffset();
        this.type =token.getType();
        //this.position = token.getPosition();
        //this.positionLength = token.getPositionLength();
    }

    @Override
    public String toString() {
        return "AddressTerm{" +
                "term='" + term + '\'' +
                ", startOffset=" + startOffset +
                ", endOffset=" + endOffset +
                ", type='" + type + '\'' +
                '}';
    }
}

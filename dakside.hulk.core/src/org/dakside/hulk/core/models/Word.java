/*
 * Copyright (C) 2014 Le Tuan Anh <tuananh.ke@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.dakside.hulk.core.models;

/**
 * Word structure
 *
 * @author Le Tuan Anh <tuananh.ke@gmail.com>
 */
public class Word {

    private String wordID;
    //private String lemma;
    private String ipa;
    private String gloss;
    private String comment;
    //private String wnSynset; // WordNet Synset ID
    //private String iliId; // Inter-lingual Index ID

    public Word() {
    }

    public Word(String wordID, String ipa, String gloss, String comment) {
        this.wordID = wordID;
        this.ipa = ipa;
        this.gloss = gloss;
        this.comment = comment;
    }

    /**
     * @return the wordID
     */
    public String getWordID() {
        return wordID;
    }

    /**
     * @param wordID the wordID to set
     */
    public void setWordID(String wordID) {
        this.wordID = wordID;
    }

    /**
     * @return the ipa
     */
    public String getIpa() {
        return ipa;
    }

    /**
     * @param ipa the ipa to set
     */
    public void setIpa(String ipa) {
        this.ipa = ipa;
    }

    /**
     * @return the gloss
     */
    public String getGloss() {
        return gloss;
    }

    /**
     * @param gloss the gloss to set
     */
    public void setGloss(String gloss) {
        this.gloss = gloss;
    }

    /**
     * @return the comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * @param comment the comment to set
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

}

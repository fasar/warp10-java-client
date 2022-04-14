package fr.avalonlab.warp10.model;

import java.util.HashMap;
import java.util.Objects;

/**
 * Selector is a string that allows to select one or several GTS.
 * Its composed of the concatenation of a classname selector and a labels selector
 *
 * <p>
 * Selector is composed with a name, labels, attributes in the format : NAME{LABELS}{ATTRIBUTES}
 * </p>
 * <p>
 *     For example:  <code>foo{label0=val0,label1=val1}{attrib1=val2,attrib2=val3}</code>
 * </p>
 * <ul>
 *     <li> Name: is the Name or Class name.
 *          URL encoded UTF-8 character string.
 *          The encoding of character { (Unicode LEFT CURLY BRACKET, 0x007B) is MANDATORY.
 *          You can describe a set of time series with a regex string.
 *          Regex string start with a ~
 *     </li>
 *     <li> Labels:
 *          Comma separated list of labels, using the syntax key=value where both key and value are URL encoded UTF-8 character strings.
 *          If a key or value contains , (Unicode COMMA, 0x002C), } (Unicode RIGHT CURLY BRACKET, 0x007D) or = (Unicode EQUALS SIGN, 0x003D), those characters MUST be encoded.
 *          You can describe a set of values with a regex string.
 *          Regex string start with a ~
 *      </li>
 *     <li> Attributes:
 *          Optional comma separated list of attributes, using the syntax key=value where both key and value are URL encoded UTF-8 character strings.
 *          If a key or value contains , (Unicode COMMA, 0x002C), } (Unicode RIGHT CURLY BRACKET, 0x007D) or = (Unicode EQUALS SIGN, 0x003D),
 *          those characters MUST be encoded. Attributes is a mutable value, setting them with update endoint is possible but not recommended.
 *          Prefer meta endpoint. You have to set ingress.parse.attributes = true in the config file
 *          You can describe a set of values with a regex string.
 *          Regex string start with a ~
 *      </li>
 * </ul>
 */
public class WSelector {
    private String className;
    private HashMap<String, String> labels;
    private HashMap<String, String> attributes;

    public WSelector(String className, HashMap<String, String> labels, HashMap<String, String> attributes) {
        this.className = className;
        this.labels = labels;
        this.attributes = attributes;
    }

    public String getClassName() {
        return className;
    }

    public HashMap<String, String> getLabels() {
        return labels;
    }

    public HashMap<String, String> getAttributes() {
        return attributes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WSelector wSelector = (WSelector) o;
        return Objects.equals(className, wSelector.className) && Objects.equals(labels, wSelector.labels) && Objects.equals(attributes, wSelector.attributes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(className, labels, attributes);
    }

    @Override
    public String toString() {
        return "WSelector{" +
                "className='" + className + '\'' +
                ", labels=" + labels +
                ", attributes=" + attributes +
                '}';
    }
}

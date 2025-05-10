package extensions.org.mybatis.generator.plugins;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.internal.util.messages.Messages;

import java.util.List;

public class LimitOffsetPlugin extends PluginAdapter {


    @Override
    public boolean validate(List<String> warnings) {
        if ("MyBatis3DynamicSql".equalsIgnoreCase(context.getTargetRuntime())) { //$NON-NLS-1$
            warnings.add(Messages.getString("Warning.30")); //$NON-NLS-1$
            return false;
        }
        return true;
    }

    @Override
    public boolean modelExampleClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        FullyQualifiedJavaType longType = new FullyQualifiedJavaType(Long.class.getName());

        Field limit = new Field("limit", longType);
        limit.setVisibility(JavaVisibility.PROTECTED);
        topLevelClass.addField(limit);

        Field offset = new Field("offset", longType);
        offset.setVisibility(JavaVisibility.PROTECTED);
        topLevelClass.addField(offset);

        Method setLimit = new Method("setLimit");
        setLimit.setVisibility(JavaVisibility.PUBLIC);
        setLimit.addParameter(new Parameter(longType, "limit"));
        setLimit.addBodyLine("this.limit = limit;");
        topLevelClass.addMethod(setLimit);

        Method getLimit = new Method("getLimit");
        getLimit.setVisibility(JavaVisibility.PUBLIC);
        getLimit.setReturnType(longType);
        getLimit.addBodyLine("return limit;");
        topLevelClass.addMethod(getLimit);

        Method setOffset = new Method("setOffset");
        setOffset.setVisibility(JavaVisibility.PUBLIC);
        setOffset.addParameter(new Parameter(longType, "offset"));
        setOffset.addBodyLine("this.offset = offset;");
        topLevelClass.addMethod(setOffset);

        Method getOffset = new Method("getOffset");
        getOffset.setVisibility(JavaVisibility.PUBLIC);
        getOffset.setReturnType(longType);
        getOffset.addBodyLine("return offset;");
        topLevelClass.addMethod(getOffset);

        return super.modelExampleClassGenerated(topLevelClass, introspectedTable);
    }

    @Override
    public boolean sqlMapSelectByExampleWithoutBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {

        XmlElement limitIfElement = new XmlElement("if"); //$NON-NLS-1$
        limitIfElement.addAttribute(new Attribute("test", "limit != null")); //$NON-NLS-1$ //$NON-NLS-2$
        limitIfElement.addElement(new TextElement("limit #{limit,jdbcType=BIGINT}"));
        element.addElement(limitIfElement);

        XmlElement offsetIfElement = new XmlElement("if"); //$NON-NLS-1$
        offsetIfElement.addAttribute(new Attribute("test", "offset != null")); //$NON-NLS-1$ //$NON-NLS-2$
        offsetIfElement.addElement(new TextElement("offset #{offset,jdbcType=BIGINT}"));
        element.addElement(offsetIfElement);

        return super.sqlMapSelectByExampleWithoutBLOBsElementGenerated(element, introspectedTable);
    }

    @Override
    public boolean sqlMapSelectByExampleWithBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {

        XmlElement limitIfElement = new XmlElement("if"); //$NON-NLS-1$
        limitIfElement.addAttribute(new Attribute("test", "limit != null")); //$NON-NLS-1$ //$NON-NLS-2$
        limitIfElement.addElement(new TextElement("limit #{limit,jdbcType=BIGINT}"));
        element.addElement(limitIfElement);

        XmlElement offsetIfElement = new XmlElement("if"); //$NON-NLS-1$
        offsetIfElement.addAttribute(new Attribute("test", "offset != null")); //$NON-NLS-1$ //$NON-NLS-2$
        offsetIfElement.addElement(new TextElement("offset #{offset,jdbcType=BIGINT}"));
        element.addElement(offsetIfElement);

        return super.sqlMapSelectByExampleWithBLOBsElementGenerated(element, introspectedTable);
    }

    @Override
    public boolean sqlMapUpdateByExampleWithBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {

        XmlElement limitIfElement = new XmlElement("if"); //$NON-NLS-1$
        limitIfElement.addAttribute(new Attribute("test", "limit != null")); //$NON-NLS-1$ //$NON-NLS-2$
        limitIfElement.addElement(new TextElement("limit #{limit,jdbcType=BIGINT}"));
        element.addElement(limitIfElement);

        return super.sqlMapUpdateByExampleWithBLOBsElementGenerated(element, introspectedTable);
    }

    @Override
    public boolean sqlMapUpdateByExampleWithoutBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {

        XmlElement limitIfElement = new XmlElement("if"); //$NON-NLS-1$
        limitIfElement.addAttribute(new Attribute("test", "limit != null")); //$NON-NLS-1$ //$NON-NLS-2$
        limitIfElement.addElement(new TextElement("limit #{limit,jdbcType=BIGINT}"));
        element.addElement(limitIfElement);

        return super.sqlMapUpdateByExampleWithoutBLOBsElementGenerated(element, introspectedTable);
    }

    @Override
    public boolean sqlMapUpdateByExampleSelectiveElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {

        XmlElement limitIfElement = new XmlElement("if"); //$NON-NLS-1$
        limitIfElement.addAttribute(new Attribute("test", "limit != null")); //$NON-NLS-1$ //$NON-NLS-2$
        limitIfElement.addElement(new TextElement("limit #{limit,jdbcType=BIGINT}"));
        element.addElement(limitIfElement);

        return super.sqlMapUpdateByExampleSelectiveElementGenerated(element, introspectedTable);
    }

    @Override
    public boolean sqlMapDeleteByExampleElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {

        XmlElement limitIfElement = new XmlElement("if"); //$NON-NLS-1$
        limitIfElement.addAttribute(new Attribute("test", "limit != null")); //$NON-NLS-1$ //$NON-NLS-2$
        limitIfElement.addElement(new TextElement("limit #{limit,jdbcType=BIGINT}"));
        element.addElement(limitIfElement);

        return super.sqlMapDeleteByExampleElementGenerated(element, introspectedTable);
    }
}

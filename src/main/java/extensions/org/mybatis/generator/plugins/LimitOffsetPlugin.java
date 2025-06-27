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
import java.util.Objects;

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
        Object enabled = introspectedTable.getTableConfiguration().getProperty("limitOffsetEnabled");
        if (Objects.nonNull(enabled) && !Boolean.parseBoolean(enabled.toString())) {
            return true;
        }


        FullyQualifiedJavaType longType = new FullyQualifiedJavaType(Long.class.getName());

        Field field = new Field("limit", longType);
        field.setVisibility(JavaVisibility.PROTECTED);
        topLevelClass.addField(field);
        context.getCommentGenerator().addFieldComment(field, introspectedTable);

        field = new Field("offset", longType);
        field.setVisibility(JavaVisibility.PROTECTED);
        topLevelClass.addField(field);
        context.getCommentGenerator().addFieldComment(field, introspectedTable);

        Method method = new Method("setLimit");
        method.setVisibility(JavaVisibility.PUBLIC);
        method.addParameter(new Parameter(longType, "limit"));
        method.addBodyLine("this.limit = limit;");
        topLevelClass.addMethod(method);
        context.getCommentGenerator().addGeneralMethodComment(method, introspectedTable);

        method = new Method("getLimit");
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setReturnType(longType);
        method.addBodyLine("return limit;");
        topLevelClass.addMethod(method);
        context.getCommentGenerator().addGeneralMethodComment(method, introspectedTable);

        method = new Method("setOffset");
        method.setVisibility(JavaVisibility.PUBLIC);
        method.addParameter(new Parameter(longType, "offset"));
        method.addBodyLine("this.offset = offset;");
        topLevelClass.addMethod(method);
        context.getCommentGenerator().addGeneralMethodComment(method, introspectedTable);

        method = new Method("getOffset");
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setReturnType(longType);
        method.addBodyLine("return offset;");
        topLevelClass.addMethod(method);
        context.getCommentGenerator().addGeneralMethodComment(method, introspectedTable);

        topLevelClass.getMethods()
                .stream()
                .filter(e -> Objects.equals("clear", e.getName()))
                .findAny().ifPresent(e -> {
                    e.getBodyLines().add("limit = null;");
                    e.getBodyLines().add("offset = null;");
                });


        return super.modelExampleClassGenerated(topLevelClass, introspectedTable);
    }

    @Override
    public boolean sqlMapSelectByExampleWithoutBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        Object enabled = introspectedTable.getTableConfiguration().getProperty("limitOffsetEnabled");
        if (Objects.nonNull(enabled) && !Boolean.parseBoolean(enabled.toString())) {
            return true;
        }


        XmlElement ifElement = new XmlElement("if"); //$NON-NLS-1$
        ifElement.addAttribute(new Attribute("test", "limit != null")); //$NON-NLS-1$ //$NON-NLS-2$
        ifElement.addElement(new TextElement("limit #{limit,jdbcType=BIGINT}"));
        element.addElement(ifElement);

        ifElement = new XmlElement("if"); //$NON-NLS-1$
        ifElement.addAttribute(new Attribute("test", "offset != null")); //$NON-NLS-1$ //$NON-NLS-2$
        ifElement.addElement(new TextElement("offset #{offset,jdbcType=BIGINT}"));
        element.addElement(ifElement);

        return super.sqlMapSelectByExampleWithoutBLOBsElementGenerated(element, introspectedTable);
    }

    @Override
    public boolean sqlMapSelectByExampleWithBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        Object enabled = introspectedTable.getTableConfiguration().getProperty("limitOffsetEnabled");
        if (Objects.nonNull(enabled) && !Boolean.parseBoolean(enabled.toString())) {
            return true;
        }

        XmlElement ifElement = new XmlElement("if"); //$NON-NLS-1$
        ifElement.addAttribute(new Attribute("test", "limit != null")); //$NON-NLS-1$ //$NON-NLS-2$
        ifElement.addElement(new TextElement("limit #{limit,jdbcType=BIGINT}"));
        element.addElement(ifElement);

        ifElement = new XmlElement("if"); //$NON-NLS-1$
        ifElement.addAttribute(new Attribute("test", "offset != null")); //$NON-NLS-1$ //$NON-NLS-2$
        ifElement.addElement(new TextElement("offset #{offset,jdbcType=BIGINT}"));
        element.addElement(ifElement);

        return super.sqlMapSelectByExampleWithBLOBsElementGenerated(element, introspectedTable);
    }

    @Override
    public boolean sqlMapUpdateByExampleWithBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        Object enabled = introspectedTable.getTableConfiguration().getProperty("limitOffsetEnabled");
        if (Objects.nonNull(enabled) && !Boolean.parseBoolean(enabled.toString())) {
            return true;
        }

        XmlElement xmlElement = new XmlElement("if"); //$NON-NLS-1$
        xmlElement.addAttribute(new Attribute("test", "example.limit != null")); //$NON-NLS-1$ //$NON-NLS-2$
        xmlElement.addElement(new TextElement("limit #{example.limit,jdbcType=BIGINT}"));
        element.addElement(xmlElement);

        return super.sqlMapUpdateByExampleWithBLOBsElementGenerated(element, introspectedTable);
    }

    @Override
    public boolean sqlMapUpdateByExampleWithoutBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        Object enabled = introspectedTable.getTableConfiguration().getProperty("limitOffsetEnabled");
        if (Objects.nonNull(enabled) && !Boolean.parseBoolean(enabled.toString())) {
            return true;
        }

        XmlElement xmlElement = new XmlElement("if"); //$NON-NLS-1$
        xmlElement.addAttribute(new Attribute("test", "example.limit != null")); //$NON-NLS-1$ //$NON-NLS-2$
        xmlElement.addElement(new TextElement("limit #{example.limit,jdbcType=BIGINT}"));
        element.addElement(xmlElement);

        return super.sqlMapUpdateByExampleWithoutBLOBsElementGenerated(element, introspectedTable);
    }

    @Override
    public boolean sqlMapUpdateByExampleSelectiveElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        Object enabled = introspectedTable.getTableConfiguration().getProperty("limitOffsetEnabled");
        if (Objects.nonNull(enabled) && !Boolean.parseBoolean(enabled.toString())) {
            return true;
        }

        XmlElement xmlElement = new XmlElement("if"); //$NON-NLS-1$
        xmlElement.addAttribute(new Attribute("test", "example.limit != null")); //$NON-NLS-1$ //$NON-NLS-2$
        xmlElement.addElement(new TextElement("limit #{example.limit,jdbcType=BIGINT}"));
        element.addElement(xmlElement);

        return super.sqlMapUpdateByExampleSelectiveElementGenerated(element, introspectedTable);
    }

    @Override
    public boolean sqlMapDeleteByExampleElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        Object enabled = introspectedTable.getTableConfiguration().getProperty("limitOffsetEnabled");
        if (Objects.nonNull(enabled) && !Boolean.parseBoolean(enabled.toString())) {
            return true;
        }

        XmlElement xmlElement = new XmlElement("if"); //$NON-NLS-1$
        xmlElement.addAttribute(new Attribute("test", "example.limit != null")); //$NON-NLS-1$ //$NON-NLS-2$
        xmlElement.addElement(new TextElement("limit #{example.limit,jdbcType=BIGINT}"));
        element.addElement(xmlElement);

        return super.sqlMapDeleteByExampleElementGenerated(element, introspectedTable);
    }

}

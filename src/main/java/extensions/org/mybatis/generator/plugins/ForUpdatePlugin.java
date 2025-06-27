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


public class ForUpdatePlugin extends PluginAdapter {


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
        Object enabled = introspectedTable.getTableConfiguration().getProperty("forUpdateEnabled");
        if (Objects.nonNull(enabled) && !Boolean.parseBoolean(enabled.toString())) {
            return true;
        }

        Field field = new Field("forUpdate", FullyQualifiedJavaType.getBooleanPrimitiveInstance());
        field.setVisibility(JavaVisibility.PROTECTED);
        topLevelClass.addField(field);
        context.getCommentGenerator().addFieldComment(field, introspectedTable);

        Method method = new Method("forUpdate");
        method.setVisibility(JavaVisibility.PUBLIC);
        method.addBodyLine("this.forUpdate = true;");
        topLevelClass.addMethod(method);
        context.getCommentGenerator().addGeneralMethodComment(method, introspectedTable);

        method = new Method("setForUpdate");
        method.addParameter(new Parameter(FullyQualifiedJavaType.getBooleanPrimitiveInstance(), "forUpdate"));
        method.setVisibility(JavaVisibility.PUBLIC);
        method.addBodyLine("this.forUpdate = forUpdate;");
        topLevelClass.addMethod(method);
        context.getCommentGenerator().addGeneralMethodComment(method, introspectedTable);

        method = new Method("getForUpdate");
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setReturnType(FullyQualifiedJavaType.getBooleanPrimitiveInstance());
        method.addBodyLine("return forUpdate;");
        topLevelClass.addMethod(method);
        context.getCommentGenerator().addGeneralMethodComment(method, introspectedTable);

        method = new Method("isForUpdate");
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setReturnType(FullyQualifiedJavaType.getBooleanPrimitiveInstance());
        method.addBodyLine("return forUpdate;");
        topLevelClass.addMethod(method);
        context.getCommentGenerator().addGeneralMethodComment(method, introspectedTable);


        topLevelClass.getMethods()
                .stream()
                .filter(e -> Objects.equals("clear", e.getName()))
                .findAny()
                .ifPresent(e -> {
                    e.getBodyLines().add("forUpdate = false;");
                });


        return super.modelExampleClassGenerated(topLevelClass, introspectedTable);
    }

    @Override
    public boolean sqlMapSelectByExampleWithoutBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        Object enabled = introspectedTable.getTableConfiguration().getProperty("forUpdateEnabled");
        if (Objects.nonNull(enabled) && !Boolean.parseBoolean(enabled.toString())) {
            return true;
        }

        XmlElement ifElement = new XmlElement("if"); //$NON-NLS-1$
        ifElement.addAttribute(new Attribute("test", "forUpdate")); //$NON-NLS-1$ //$NON-NLS-2$
        ifElement.addElement(new TextElement("for update"));
        element.addElement(ifElement);

        return super.sqlMapSelectByExampleWithoutBLOBsElementGenerated(element, introspectedTable);
    }

    @Override
    public boolean sqlMapSelectByExampleWithBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        Object enabled = introspectedTable.getTableConfiguration().getProperty("forUpdateEnabled");
        if (Objects.nonNull(enabled) && !Boolean.parseBoolean(enabled.toString())) {
            return true;
        }

        XmlElement ifElement = new XmlElement("if"); //$NON-NLS-1$
        ifElement.addAttribute(new Attribute("test", "forUpdate")); //$NON-NLS-1$ //$NON-NLS-2$
        ifElement.addElement(new TextElement("for update"));
        element.addElement(ifElement);

        return super.sqlMapSelectByExampleWithBLOBsElementGenerated(element, introspectedTable);
    }
}

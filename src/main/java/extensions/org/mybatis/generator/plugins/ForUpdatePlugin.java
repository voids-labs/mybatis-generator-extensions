package extensions.org.mybatis.generator.plugins;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
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

        Field forUpdate = new Field("forUpdate", FullyQualifiedJavaType.getBooleanPrimitiveInstance());
        forUpdate.setVisibility(JavaVisibility.PROTECTED);
        topLevelClass.addField(forUpdate);

        Method setForUpdate = new Method("forUpdate");
        setForUpdate.setVisibility(JavaVisibility.PUBLIC);
        setForUpdate.addBodyLine("this.forUpdate = true;");
        topLevelClass.addMethod(setForUpdate);

        Method getForUpdate = new Method("getForUpdate");
        getForUpdate.setVisibility(JavaVisibility.PUBLIC);
        getForUpdate.setReturnType(FullyQualifiedJavaType.getBooleanPrimitiveInstance());
        getForUpdate.addBodyLine("return forUpdate;");
        topLevelClass.addMethod(getForUpdate);

        topLevelClass.getMethods()
                .stream()
                .filter(e -> Objects.equals("clear", e.getName()))
                .findAny().ifPresent(e -> {
                    e.getBodyLines().add("forUpdate = false;");
                });


        return super.modelExampleClassGenerated(topLevelClass, introspectedTable);
    }

    @Override
    public boolean sqlMapSelectByExampleWithoutBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {

        XmlElement ifElement = new XmlElement("if"); //$NON-NLS-1$
        ifElement.addAttribute(new Attribute("test", "forUpdate")); //$NON-NLS-1$ //$NON-NLS-2$
        ifElement.addElement(new TextElement("for update"));
        element.addElement(ifElement);

        return super.sqlMapSelectByExampleWithoutBLOBsElementGenerated(element, introspectedTable);
    }

    @Override
    public boolean sqlMapSelectAllElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {

        XmlElement ifElement = new XmlElement("if"); //$NON-NLS-1$
        ifElement.addAttribute(new Attribute("test", "forUpdate")); //$NON-NLS-1$ //$NON-NLS-2$
        ifElement.addElement(new TextElement("for update"));
        element.addElement(ifElement);

        return super.sqlMapSelectAllElementGenerated(element, introspectedTable);
    }

    @Override
    public boolean sqlMapSelectByPrimaryKeyElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {

        XmlElement ifElement = new XmlElement("if"); //$NON-NLS-1$
        ifElement.addAttribute(new Attribute("test", "forUpdate")); //$NON-NLS-1$ //$NON-NLS-2$
        ifElement.addElement(new TextElement("for update"));
        element.addElement(ifElement);

        return super.sqlMapSelectByPrimaryKeyElementGenerated(element, introspectedTable);
    }

    @Override
    public boolean sqlMapSelectByExampleWithBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {

        XmlElement ifElement = new XmlElement("if"); //$NON-NLS-1$
        ifElement.addAttribute(new Attribute("test", "forUpdate")); //$NON-NLS-1$ //$NON-NLS-2$
        ifElement.addElement(new TextElement("for update"));
        element.addElement(ifElement);

        return super.sqlMapSelectByExampleWithBLOBsElementGenerated(element, introspectedTable);
    }
}

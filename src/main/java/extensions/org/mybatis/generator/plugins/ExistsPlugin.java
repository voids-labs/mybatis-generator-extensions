package extensions.org.mybatis.generator.plugins;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.AbstractXmlElementGenerator;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.internal.util.messages.Messages;

import java.util.List;
import java.util.Objects;


public class ExistsPlugin extends PluginAdapter {

    private List<String> warnings;
    @Override
    public boolean validate(List<String> warnings) {
        if ("MyBatis3DynamicSql".equalsIgnoreCase(context.getTargetRuntime())) { //$NON-NLS-1$
            warnings.add(Messages.getString("Warning.30")); //$NON-NLS-1$
            return false;
        }
        this.warnings = warnings;
        return true;
    }

    @Override
    public boolean clientGenerated(Interface interfaze,
                                   TopLevelClass topLevelClass,
                                   IntrospectedTable introspectedTable) {
        Object enabled = introspectedTable.getTableConfiguration().getProperty("existsByExampleEnabled");
        if (Objects.nonNull(enabled) && !Boolean.parseBoolean(enabled.toString())) {
            return true;
        }

        Method method = new Method("existsByExample");
        method.addParameter(new Parameter(new FullyQualifiedJavaType(introspectedTable.getExampleType()), "example"));
        method.setReturnType(FullyQualifiedJavaType.getBooleanPrimitiveInstance());
        context.getCommentGenerator().addGeneralMethodComment(method, introspectedTable);

        interfaze.addMethod(method);

        return super.clientGenerated(interfaze, topLevelClass, introspectedTable);
    }

    @Override
    public boolean sqlMapDocumentGenerated(Document document,
                                           IntrospectedTable introspectedTable) {
        Object enabled = introspectedTable.getTableConfiguration().getProperty("existsByExampleStatementEnabled");
        if (Objects.nonNull(enabled) && !Boolean.parseBoolean(enabled.toString())) {
            return true;
        }

        new ExistsByExampleElementGenerator(introspectedTable.getContext(), introspectedTable, this.warnings, document.getRootElement());
        return super.sqlMapDocumentGenerated(document, introspectedTable);
    }

    static class ExistsByExampleElementGenerator extends AbstractXmlElementGenerator {

        public ExistsByExampleElementGenerator(Context context,
                                               IntrospectedTable introspectedTable,
                                               List<String> warnings,
                                               XmlElement parentElement
        ) {
            super();
            setContext(context);
            setIntrospectedTable(introspectedTable);
            setWarnings(warnings);
            addElements(parentElement);
        }

        @Override
        public void addElements(XmlElement parentElement) {
            Object existsByExampleStatementEnabled = introspectedTable.getTableConfiguration().getProperty("existsByExampleStatementEnabled");
            if (Objects.nonNull(existsByExampleStatementEnabled) && !Boolean.parseBoolean(existsByExampleStatementEnabled.toString())) {
                return;
            }

            XmlElement answer = new XmlElement("select"); //$NON-NLS-1$

            String fqjt = introspectedTable.getExampleType();

            answer.addAttribute(new Attribute(
                    "id", "existsByExample")); //$NON-NLS-1$
            answer.addAttribute(new Attribute("parameterType", fqjt)); //$NON-NLS-1$
            answer.addAttribute(new Attribute("resultType", "boolean")); //$NON-NLS-1$ //$NON-NLS-2$

            context.getCommentGenerator().addComment(answer);

            StringBuilder sb = new StringBuilder();
            sb.append("select 1 from "); //$NON-NLS-1$
            sb.append(introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime());
            answer.addElement(new TextElement(sb.toString()));
            answer.addElement(getExampleIncludeElement());

            XmlElement ifElement = new XmlElement("if");
            ifElement.addAttribute(new Attribute("test", "limit != null"));
            ifElement.addElement(new TextElement("limit ${limit,jdbcType=BIGINT}"));

            answer.addElement(ifElement);

            parentElement.addElement(answer);

        }
    }

}

package com.java.ee.working.salaryitemtype;

import java.beans.IntrospectionException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.yaml.snakeyaml.DumperOptions.FlowStyle;
import org.yaml.snakeyaml.error.YAMLException;
import org.yaml.snakeyaml.introspector.Property;
import org.yaml.snakeyaml.nodes.MappingNode;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.NodeId;
import org.yaml.snakeyaml.nodes.NodeTuple;
import org.yaml.snakeyaml.nodes.ScalarNode;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.representer.Represent;
import org.yaml.snakeyaml.representer.Representer;

public class StandardConfigurationYamlRepresenter extends Representer {
	public StandardConfigurationYamlRepresenter() {
        this.representers.put(SalaryItemTypeYamlModel.class, new RepresentSalaryItemType());
        this.representers.put(SalaryItemTypeGroupYamlModel.class, new RepresentSalaryItemTypeGroup());
        this.representers.put(SalaryConfigurationYamlModel.class, new RepresentSalaryConfiguration());
        this.representers.put(VariableYamlModel.class, new RepresentVariable());
    }

	private enum PresenterType {
		SALARY_ITEM_TYPE, SALARY_ITEM_TYPE_GROUP, SALARY_CONFIGURATION, VARIABLE;
	}
	
	private class RepresentVariable implements Represent {
        public Node representData(Object data) {
            try {
				return getMappingNode(getProperties(data.getClass()), data, false, PresenterType.VARIABLE);
			} catch (IntrospectionException e) {
				throw new YAMLException(e);
			}
        }
    }
	
    private class RepresentSalaryItemType implements Represent {
        public Node representData(Object data) {
            try {
				return getMappingNode(getProperties(data.getClass()), data, true, PresenterType.SALARY_ITEM_TYPE);
			} catch (IntrospectionException e) {
				throw new YAMLException(e);
			}
        }
    }
    
    private class RepresentSalaryItemTypeGroup implements Represent {
        public Node representData(Object data) {
            try {
				return getMappingNode(getProperties(data.getClass()), data, true, PresenterType.SALARY_ITEM_TYPE_GROUP);
			} catch (IntrospectionException e) {
				throw new YAMLException(e);
			}
        }
    }
    
    private class RepresentSalaryConfiguration implements Represent {
        public Node representData(Object data) {
            try {
				return getMappingNode(getProperties(data.getClass()), data, true, PresenterType.SALARY_CONFIGURATION);
			} catch (IntrospectionException e) {
				throw new YAMLException(e);
			}
        }
    }

    protected MappingNode getMappingNode(Set<Property> properties, Object javaBean, Boolean flowStyle, PresenterType presenterType) {
		List<NodeTuple> value = new ArrayList<NodeTuple>(properties.size());
		Tag tag;
		Tag customTag = Tag.MAP;
		tag = customTag != null ? customTag : new Tag(javaBean.getClass());
		MappingNode node = new MappingNode(tag, value, null);
		representedObjects.put(javaBean, node);
		boolean bestStyle = true;
		for (Property property : properties) {
			if ("id".equals(property.getName())) {
				continue;
			}
			
			Object memberValue = property.get(javaBean);
			memberValue = setMemberValue(property, memberValue, presenterType);
			
			Tag customPropertyTag = memberValue == null ? null : classTags.get(memberValue.getClass());
			NodeTuple tuple = representJavaBeanProperty(javaBean, property, memberValue, customPropertyTag);

			if (tuple == null) {
				continue;
			}
			bestStyle = checkBestStyle(tuple);
			value.add(tuple);
			if (flowStyle == false) {
				setDefaultFlowStyle(FlowStyle.AUTO);
			} else {
				setDefaultFlowStyle(FlowStyle.BLOCK);
			}
		}
		if (defaultFlowStyle != FlowStyle.AUTO) {
			node.setFlowStyle(defaultFlowStyle.getStyleBoolean());
		} else {
			node.setFlowStyle(bestStyle);
		}
		return node;
	}
    private Object setMemberValue(Property property, Object memberValuepr, PresenterType presenterType){
        Object memberValue = memberValuepr;
        List<String> propertyNames = Arrays.asList("formula", "value", "name", "code", "salaryItemTypeCodes", "salaryItemTypeGroupCodes");
        if (propertyNames.contains(property.getName())) {
            if (memberValue != null) {
                memberValue = addQuoteToString(memberValue);
            } else {
                memberValue = addQuoteToString("");
            }
        }

        if ("description".equals(property.getName()) || "tag".equals(property.getName())) {
            if (presenterType.equals(PresenterType.SALARY_ITEM_TYPE)) {
                memberValue = improvedMapI18NData(memberValue);
            } else {
                memberValue = addQuoteToString(memberValue);
            }
        }
        return memberValue;
    }
    private boolean checkBestStyle(NodeTuple tuple){
        boolean bestStyle = true;
        if (((ScalarNode) tuple.getKeyNode()).getStyle() != null) {
            bestStyle = false;
        }
        Node nodeValue = tuple.getValueNode();
        if (!(nodeValue instanceof ScalarNode && ((ScalarNode) nodeValue).getStyle() == null)) {
            bestStyle = false;
        }
        return bestStyle;
    }
	Object addQuoteToString(Object memberValuepr) {
	    Object memberValue = memberValuepr;
		memberValue = "\"" + memberValue + "\"";
		return memberValue;
	}

	@SuppressWarnings("unchecked")
	private Object improvedMapI18NData(Object memberValuepr) {
	    Object memberValue = memberValuepr;
		Map<String, String> improvedContentI18N = new HashMap<String, String>();
		improvedContentI18N = (HashMap<String, String>)memberValue;
		for (Entry<String, String> entry : improvedContentI18N.entrySet()) {
			String value = entry.getValue() != null ? "\"" + entry.getValue() + "\"" : null;
			improvedContentI18N.put(entry.getKey(), value);
		}
		memberValue = improvedContentI18N;
		return memberValue;
	}
	
    @Override
	protected NodeTuple representJavaBeanProperty(Object javaBean, Property property,
            Object propertyValue, Tag customTag) {
        ScalarNode nodeKey = (ScalarNode) representData(property.getName());
        // the first occurrence of the node must keep the tag
        boolean hasAlias = this.representedObjects.containsKey(propertyValue);

        Node nodeValue = representData(propertyValue);

        if (propertyValue != null && !hasAlias) {
            NodeId nodeId = nodeValue.getNodeId();
            if (customTag == null) {
                nodeValue = setTagforNodeValue(property, propertyValue, nodeValue, nodeId);
            }
        }

        return new NodeTuple(nodeKey, nodeValue);
    }

    private Node setTagforNodeValue(Property property, Object propertyValue, Node nodeValue, NodeId nodeId) {
        if (nodeId == NodeId.scalar) {
            if (propertyValue instanceof Enum<?>) {
                nodeValue.setTag(Tag.STR);
            }
        } else {
            if (nodeId == NodeId.mapping) {
                if (property.getType() == propertyValue.getClass() && !(propertyValue instanceof Map<?, ?>) && !nodeValue.getTag().equals(Tag.SET)) {
                    nodeValue.setTag(Tag.MAP);
                }
                checkGlobalTag(property, nodeValue, propertyValue);
            }
        }
        return nodeValue;
    }
}

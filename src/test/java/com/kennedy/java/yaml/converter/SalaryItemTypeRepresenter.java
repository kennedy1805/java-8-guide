package com.kennedy.java.yaml.converter;

import java.beans.IntrospectionException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
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

import com.kennedy.java.yaml.converter.model.SalaryItemType;
import com.kennedy.java.yaml.converter.model.Variable;

public class SalaryItemTypeRepresenter extends Representer {
	public SalaryItemTypeRepresenter() {
		this.representers.put(SalaryItemType.class, new RepresentSalaryItemType());
		this.representers.put(Variable.class, new RepresentVariable());
	}

	private class RepresentVariable implements Represent {
		public Node representData(Object data) {
			try {
				return getMappingNode(getProperties(data.getClass()), data, false);
			} catch (IntrospectionException e) {
				throw new YAMLException(e);
			}
		}
	}

	private class RepresentSalaryItemType implements Represent {
		public Node representData(Object data) {
			try {
				return getMappingNode(getProperties(data.getClass()), data, true);
			} catch (IntrospectionException e) {
				throw new YAMLException(e);
			}
		}
	}

	protected MappingNode getMappingNode(Set<Property> properties, Object javaBean, Boolean flowStyle) {
		List<NodeTuple> value = new ArrayList<NodeTuple>(properties.size());
		Tag tag;
		Tag customTag = Tag.MAP;
		tag = customTag != null ? customTag : new Tag(javaBean.getClass());
		MappingNode node = new MappingNode(tag, value, null);
		representedObjects.put(javaBean, node);
		boolean bestStyle = true;
		for (Property property : properties) {
			Object memberValue = property.get(javaBean);
			List<String> propertyNames = Arrays.asList("formula", "value", "name", "code");
			if (propertyNames.contains(property.getName())) {
				memberValue = "\"" + memberValue + "\"";
			}

			if ("description".equals(property.getName())) {
				memberValue = improvedDescription(memberValue);
			}
			
			Tag customPropertyTag = memberValue == null ? null : classTags.get(memberValue.getClass());
			NodeTuple tuple = representJavaBeanProperty(javaBean, property, memberValue, customPropertyTag);

			if (tuple == null) {
				continue;
			}
			if (((ScalarNode) tuple.getKeyNode()).getStyle() != null) {
				bestStyle = false;
			}
			Node nodeValue = tuple.getValueNode();
			if (!(nodeValue instanceof ScalarNode && ((ScalarNode) nodeValue).getStyle() == null)) {
				bestStyle = false;
			}
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

	private Object improvedDescription(Object memberValue) {
		Map<String, String> improvedDescription = new LinkedHashMap<String, String>();
		improvedDescription = (LinkedHashMap<String, String>)memberValue;
		for (Entry<String, String> entry : improvedDescription.entrySet()) {
			improvedDescription.put(entry.getKey(), "\"" + entry.getValue() + "\"");
		}
		memberValue = improvedDescription;
		return memberValue;
	}

	protected NodeTuple representJavaBeanProperty(Object javaBean, Property property, Object propertyValue,
			Tag customTag) {
		ScalarNode nodeKey = (ScalarNode) representData(property.getName());
		// the first occurrence of the node must keep the tag
		boolean hasAlias = this.representedObjects.containsKey(propertyValue);

		Node nodeValue = representData(propertyValue);

		if (propertyValue != null && !hasAlias) {
			NodeId nodeId = nodeValue.getNodeId();
			if (customTag == null) {
				if (nodeId == NodeId.scalar) {
					if (propertyValue instanceof Enum<?>) {
						nodeValue.setTag(Tag.STR);
					}
				} else {
					if (nodeId == NodeId.mapping) {
						if (property.getType() == propertyValue.getClass()) {
							if (!(propertyValue instanceof Map<?, ?>)) {
								if (!nodeValue.getTag().equals(Tag.SET)) {
									nodeValue.setTag(Tag.MAP);
								}
							}
						}
					}
					checkGlobalTag(property, nodeValue, propertyValue);
				}
			}
		}

		return new NodeTuple(nodeKey, nodeValue);
	}
}

package org.eclipse.milo.opcua.stack.core.types.structured;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.eclipse.milo.opcua.stack.core.NamespaceTable;
import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part4/7.28">https://reference.opcfoundation.org/v105/Core/docs/Part4/7.28</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public class QueryDataSet extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=577");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=579");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=578");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15202");

    private final ExpandedNodeId nodeId;

    private final ExpandedNodeId typeDefinitionNode;

    private final Variant[] values;

    public QueryDataSet(ExpandedNodeId nodeId, ExpandedNodeId typeDefinitionNode, Variant[] values) {
        this.nodeId = nodeId;
        this.typeDefinitionNode = typeDefinitionNode;
        this.values = values;
    }

    @Override
    public ExpandedNodeId getTypeId() {
        return TYPE_ID;
    }

    @Override
    public ExpandedNodeId getBinaryEncodingId() {
        return BINARY_ENCODING_ID;
    }

    @Override
    public ExpandedNodeId getXmlEncodingId() {
        return XML_ENCODING_ID;
    }

    @Override
    public ExpandedNodeId getJsonEncodingId() {
        return JSON_ENCODING_ID;
    }

    public ExpandedNodeId getNodeId() {
        return nodeId;
    }

    public ExpandedNodeId getTypeDefinitionNode() {
        return typeDefinitionNode;
    }

    public Variant[] getValues() {
        return values;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 579),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("NodeId", LocalizedText.NULL_VALUE, new NodeId(0, 18), -1, null, UInteger.valueOf(0), false),
                new StructureField("TypeDefinitionNode", LocalizedText.NULL_VALUE, new NodeId(0, 18), -1, null, UInteger.valueOf(0), false),
                new StructureField("Values", LocalizedText.NULL_VALUE, new NodeId(0, 24), 1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<QueryDataSet> {
        @Override
        public Class<QueryDataSet> getType() {
            return QueryDataSet.class;
        }

        @Override
        public QueryDataSet decode(SerializationContext context, UaDecoder decoder) {
            ExpandedNodeId nodeId = decoder.readExpandedNodeId("NodeId");
            ExpandedNodeId typeDefinitionNode = decoder.readExpandedNodeId("TypeDefinitionNode");
            Variant[] values = decoder.readVariantArray("Values");
            return new QueryDataSet(nodeId, typeDefinitionNode, values);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, QueryDataSet value) {
            encoder.writeExpandedNodeId("NodeId", value.getNodeId());
            encoder.writeExpandedNodeId("TypeDefinitionNode", value.getTypeDefinitionNode());
            encoder.writeVariantArray("Values", value.getValues());
        }
    }
}

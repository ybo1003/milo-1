package org.eclipse.milo.opcua.stack.core.types.structured;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.eclipse.milo.opcua.stack.core.NamespaceTable;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part14/6.2.4/#6.2.4.5.3">https://reference.opcfoundation.org/v105/Core/docs/Part14/6.2.4/#6.2.4.5.3</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public abstract class DataSetWriterMessageDataType extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=15605");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=15688");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=15987");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=16158");

    public DataSetWriterMessageDataType() {
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

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 15688),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{

            }
        );
    }
}
